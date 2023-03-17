package com.AE.sgmis.controller;

import com.AE.sgmis.pojo.LoginMsg;
import com.AE.sgmis.pojo.User;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.LoginService;
import com.AE.sgmis.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Data
    private static class ParamUser {
        String account;
        String password;
    }

    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 验证登录
     */
    @PostMapping("/authority")
    public Result login(@RequestBody ParamUser paramUser, HttpServletResponse response, HttpServletRequest request) {
        //装配为二进制
        User user = new User();
        user.setAccount(paramUser.account);
        user.setPassword(paramUser.password.getBytes());

        //验证密码
        loginService.loginVerify(user);

        //为用户生成token
        String token = jwtUtil.getToken(paramUser.account);

        //cookie添加用户信息
        Cookie cookie = new Cookie("user", paramUser.account);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);

        //添加登录信息
        LoginMsg loginMsg = new LoginMsg();
        loginMsg.setUser(paramUser.account);
        loginMsg.setIp(request.getRemoteAddr());
        loginMsg.setDate(new Date());
        loginService.addLoginMsg(loginMsg);

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
    public Result exit(HttpSession session, HttpServletRequest request) {
        session.invalidate();

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                cookie.setMaxAge(0);
                break;
            }
        }

        return new Result(SuccessCode.ExitSuccess.code, "退出成功");
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
