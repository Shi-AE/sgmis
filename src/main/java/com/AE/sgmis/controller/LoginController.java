package com.AE.sgmis.controller;

import com.AE.sgmis.pojo.User;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Data
    private static class ParamUser {
        String account;
        String password;
    }

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result handle(@RequestBody ParamUser paramUser, HttpSession session, HttpServletResponse response) {
        //装配为二进制
        User user = new User();
        user.setAccount(paramUser.account);
        user.setPassword(paramUser.password.getBytes());

        //验证密码
        loginService.loginVerify(user);

        //登录成功，在session中加入用户信息
        session.setAttribute("user", paramUser.account);

        //cookie添加用户信息
        Cookie cookie = new Cookie("user", paramUser.account);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);

        return new Result(SuccessCode.LoginSuccess.code, "登录成功");
    }
}
