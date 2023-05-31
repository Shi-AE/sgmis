package com.AE.sgmis.controller;

import com.AE.sgmis.exceptions.ConfirmPasswordInconsistencyException;
import com.AE.sgmis.exceptions.FreePassException;
import com.AE.sgmis.exceptions.SaveFailException;
import com.AE.sgmis.exceptions.UnchangedPasswordException;
import com.AE.sgmis.pojo.LoginMsg;
import com.AE.sgmis.pojo.UpdateUserVo;
import com.AE.sgmis.pojo.User;
import com.AE.sgmis.pojo.UserVo;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.LoginMsgService;
import com.AE.sgmis.service.LoginService;
import com.AE.sgmis.util.IpUtil;
import com.AE.sgmis.util.JwtUtil;
import com.AE.sgmis.util.WhitelistUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.AccessException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginMsgService loginMsgService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private IpUtil ipUtil;
    @Autowired
    private WhitelistUtil whitelistUtil;
    @Value("${page.maxLimit}")
    private Integer limit;

    /**
     * 验证登录
     */
    @PostMapping("authority")
    public Result login(@RequestBody UserVo userVo, HttpServletRequest request) {
        String account = userVo.getAccount();
        String password = userVo.getPassword();

        //装配为二进制
        User user = new User();
        user.setAccount(account);
        user.setPassword(password.getBytes());

        //验证密码并更新加密
        user = loginService.loginVerify(user);
        loginService.updateEncrypt(user);

        //拿取用户信息
        String ip = ipUtil.getIp(request);
        //解析请求头中User-Agent
        String userAgent = request.getHeader("User-Agent");
        UserAgent parseUserAgent = UserAgent.parseUserAgentString(userAgent);
        //获取用户设备信息
        Browser browser = parseUserAgent.getBrowser();
        Version browserVersion = parseUserAgent.getBrowserVersion();
        OperatingSystem os = parseUserAgent.getOperatingSystem();
        Long id = user.getId();
        Long gid = user.getGid();
        Boolean admin = user.getAdmin();

        //把用户信息集合成map
        Map<String, Object> claim = new HashMap<>();
        claim.put("id", id);
        claim.put("account", account);
        claim.put("gid", gid);
        claim.put("admin", admin);
        claim.put("ip", ip);

        //传入用户信息map，为用户生成token
        String token = jwtUtil.getLimitlessToken(claim);

        //将token存入redis
        whitelistUtil.setToken(id, ip, token);

        //根据uid最大限制剔除多余的ip下的token
        whitelistUtil.limitToken(id);

        //记录登录日志
        LoginMsg loginMsg = new LoginMsg();
        loginMsg.setIp(ip);
        loginMsg.setGid(gid);
        loginMsg.setTime(LocalDateTime.now());
        loginMsg.setAccount(account);
        loginMsg.setBrowser(browser.getName() + browserVersion.getVersion());
        loginMsg.setOs(os.getName());
        loginMsg.setDevice(os.getDeviceType().getName());
        boolean success = loginMsgService.save(loginMsg);
        if (!success) {
            log.error("记录登录信息 {} 出错", loginMsg);
            throw new SaveFailException("登录出错");
        }

        //返回给前端token
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        resultMap.put("admin", admin);

        return new Result(resultMap, SuccessCode.LoginSuccess.code, "登录成功");
    }

    /**
     * 普通前端访问网页权限登录验证
     */
    @GetMapping
    public Result verifyLogin() {
        //普通请求由请求头控制
        return new Result(SuccessCode.AccessSuccess.code, "访问通过");
    }

    @GetMapping("admin")
    public Result verifyAdmin() {
        //管理员请求由管理员请求头控制
        return new Result(SuccessCode.AccessSuccess.code, "访问通过");
    }

    /**
     * 免登录验证
     */
    @GetMapping("free")
    public Result freePass(HttpServletRequest request) {
        try {
            //获取请求头证书
            String token = request.getHeader("Authorization");

            //验证证书
            DecodedJWT decoded = jwtUtil.verifyToken(token);

            //获取token中的信息
            Map<String, Object> claim = decoded.getClaim("info").asMap();

            //在redis中验证证书
            Long id = (Long) claim.get("id");
            String ip = (String) claim.get("ip");
            boolean verified = whitelistUtil.verifyToken(id, ip, token);
            if (!verified) {
                throw new AccessException("登录过期");
            }
        } catch (AccessException | JWTVerificationException e) {
            throw new FreePassException("免登录失败");
        }

        return new Result(SuccessCode.Success.code, "登录成功");
    }

    /**
     * 退出登录
     */
    @DeleteMapping
    public Result exit(HttpSession session) {
        //销毁session
        session.invalidate();
        return new Result(SuccessCode.ExitSuccess.code, "退出成功");
    }

    /**
     * 修改密码
     */
    @PutMapping
    public Result updatePassword(@RequestBody UpdateUserVo userVo, HttpServletRequest request) {
        if (!userVo.getNewPassword().equals(userVo.getConfirmPassword())) {
            throw new ConfirmPasswordInconsistencyException("新密码和确认密码不一致");
        }
        if (userVo.getOldPassword().equals(userVo.getNewPassword())) {
            throw new UnchangedPasswordException("密码未变更");
        }

        //装配为二进制
        User user = new User();
        user.setAccount(userVo.getAccount());
        user.setPassword(userVo.getOldPassword().getBytes());

        //验证原密码
        loginService.loginVerify(user);

        //装配为现密码
        user.setPassword(userVo.getNewPassword().getBytes());

        //添加id
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long id = (Long) info.get("id");
        user.setId(id);

        //更新密码，并加密
        loginService.updateEncrypt(user);

        //剔除线上token
        whitelistUtil.deleteToken(id);

        return new Result(SuccessCode.ModifyPasswordSuccess.code, "修改密码成功");
    }

    /**
     * 获取所有登录信息
     * 避免过多查询设置限制
     */
    @GetMapping("message")
    public Result getLoginMessage(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //限制一下总数
        QueryWrapper<LoginMsg> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid)
                .orderByDesc("time")
                .last("LIMIT " + limit);

        List<LoginMsg> list = loginMsgService.list(wrapper);

        return new Result(list, SuccessCode.Success.code, "查询成功");
    }
}
