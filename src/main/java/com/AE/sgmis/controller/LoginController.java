package com.AE.sgmis.controller;

import com.AE.sgmis.pojo.User;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.LoginService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Data
    static class ParamUser {
        String account;
        String password;
    }

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result handle(@RequestBody ParamUser paramUser) {
        User user = new User();
        user.setAccount(paramUser.account);
        user.setPassword(paramUser.password.getBytes());
        loginService.loginVerify(user);
        return new Result(SuccessCode.LoginSuccess.code, "登录成功");
    }
}
