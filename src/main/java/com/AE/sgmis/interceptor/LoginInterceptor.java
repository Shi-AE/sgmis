package com.AE.sgmis.interceptor;

import com.AE.sgmis.util.JwtUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取请求头证书
        String token = request.getHeader("Authorization");

        //验证证书
        DecodedJWT decodedJWT = jwtUtil.verifyToken(token);

        //向请求域中添加用户信息
        String id = decodedJWT.getClaim("id").asString();
        String account = decodedJWT.getClaim("account").asString();
        request.setAttribute("id", id);
        request.setAttribute("account", account);

        return true;
    }
}
