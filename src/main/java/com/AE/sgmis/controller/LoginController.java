package com.AE.sgmis.controller;

import com.AE.sgmis.exceptions.AccessException;
import com.AE.sgmis.exceptions.ConfirmPasswordInconsistencyException;
import com.AE.sgmis.exceptions.SaveFailException;
import com.AE.sgmis.exceptions.UnchangedPasswordException;
import com.AE.sgmis.pojo.LoginMsg;
import com.AE.sgmis.pojo.ParamUser;
import com.AE.sgmis.pojo.UpdateUserInfo;
import com.AE.sgmis.pojo.User;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.LoginMsgService;
import com.AE.sgmis.service.LoginService;
import com.AE.sgmis.util.IpUtil;
import com.AE.sgmis.util.JwtUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 验证登录
     */
    @PostMapping("authority")
    public Result login(@RequestBody ParamUser paramUser, HttpServletRequest request) {
        String account = paramUser.getAccount();
        String password = paramUser.getPassword();

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
        String token = jwtUtil.getToken(claim);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("admin", admin);

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

        return new Result(map, SuccessCode.LoginSuccess.code, "登录成功");
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
    public Result verifyAdmin(HttpServletRequest request) {
        //获取用户信息
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long id = (Long) info.get("id");
        //请求头判断
        Boolean admin = (Boolean) info.get("admin");
        if (!admin) {
            throw new AccessException("你无权访问该页面，请联系管理员开通权限");
        }
        //数据库检索防止信息篡改
        User user = loginService.getById(id);
        if (!user.getAdmin()) {
            throw new AccessException("请求头信息异常");
        }
        return new Result(SuccessCode.AccessSuccess.code, "访问通过");
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
    public Result updatePassword(@RequestBody UpdateUserInfo userInfo, HttpServletRequest request) {
        if (!userInfo.getNewPassword().equals(userInfo.getConfirmPassword())) {
            throw new ConfirmPasswordInconsistencyException("新密码和确认密码不一致");
        }
        if (userInfo.getOldPassword().equals(userInfo.getNewPassword())) {
            throw new UnchangedPasswordException("密码未变更");
        }

        //装配为二进制
        User user = new User();
        user.setAccount(userInfo.getAccount());
        user.setPassword(userInfo.getOldPassword().getBytes());

        //验证原密码
        loginService.loginVerify(user);

        //装配为现密码
        user.setPassword(userInfo.getNewPassword().getBytes());

        //添加id
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long id = (Long) info.get("id");
        user.setId(id);

        //更新密码，并加密
        loginService.updateEncrypt(user);

        return new Result(SuccessCode.ModifyPasswordSuccess.code, "修改密码成功");
    }
}
