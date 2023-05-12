package com.AE.sgmis.interceptor;

import com.AE.sgmis.util.JwtUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;
    @Value("${jwt.update-gap}")
    private Integer updateGap;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取请求头证书
        String token = request.getHeader("Authorization");

        //验证证书
        DecodedJWT decoded = jwtUtil.verifyToken(token);

        //获取签发时间
        Date issuedAt = decoded.getIssuedAt();
        if (issuedAt == null) {
            log.error("通行证信息错误，不存在签发时间");
            return false;
        }
        //如果距离签发时间达到设定时间则重新签发
        Date date = new Date();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(date.getTime() - issuedAt.getTime());
        if (minutes >= updateGap) {
            String refreshToken = jwtUtil.getToken(decoded.getClaim("info").asMap());
            response.setHeader("Authorization", refreshToken);
        }

        //向请求域中添加用户信息
        request.setAttribute("info", decoded.getClaim("info").asMap());

        return true;
    }
}
