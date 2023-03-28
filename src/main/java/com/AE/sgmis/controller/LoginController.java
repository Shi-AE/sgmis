package com.AE.sgmis.controller;

import com.AE.sgmis.exception.ConfirmPasswordInconsistencyException;
import com.AE.sgmis.exception.UnchangedPasswordException;
import com.AE.sgmis.pojo.LoginMsg;
import com.AE.sgmis.pojo.ParamUser;
import com.AE.sgmis.pojo.UpdateUserInfo;
import com.AE.sgmis.pojo.User;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.LoginService;
import com.AE.sgmis.util.JwtUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 验证登录
     */
    @PostMapping("/authority")
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

        //把用户信息集合成map
        String ip = request.getRemoteAddr();
        Long id = user.getId();
        Long gid = user.getGid();
        Boolean admin = user.getAdmin();

        Map<String, String> claim = new HashMap<>();

        claim.put("id", String.valueOf(id));
        claim.put("account", account);
        claim.put("gid", String.valueOf(gid));
        claim.put("admin", String.valueOf(admin));
        claim.put("ip", ip);

        //传入用户信息map，为用户生成token
        String token = jwtUtil.getToken(claim);

        return new Result(token, SuccessCode.LoginSuccess.code, "登录成功");
    }

    /**
     * 普通前端访问网页权限登录验证
     */
    @GetMapping
    public Result verifyLogin() {
        //普通请求由请求头控制
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
        DecodedJWT decoded = (DecodedJWT) request.getAttribute("decoded");
        user.setId(Long.valueOf(decoded.getClaim("id").asString()));

        //更新密码，并加密
        loginService.updateEncrypt(user);

        return new Result(SuccessCode.ModifyPasswordSuccess.code, "修改密码成功");
    }

    /**
     * 获取历史登录信息
     */
    @GetMapping("/getLoginMsg")
    public Result getLoginMsg() {
        List<LoginMsg> loginMsgList = loginService.getLoginMsgList();
        System.out.println(loginMsgList);
        return new Result(loginMsgList, SuccessCode.Success.code, "查询成功");
    }
}
