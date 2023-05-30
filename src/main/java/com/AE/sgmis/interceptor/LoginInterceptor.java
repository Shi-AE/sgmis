package com.AE.sgmis.interceptor;

import com.AE.sgmis.util.JwtUtil;
import com.AE.sgmis.util.WhitelistUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private WhitelistUtil whitelistUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AccessException {
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

        //如果距离redis过期时间达到设定时间则重置redis过期时间
        whitelistUtil.updateExpires(id, ip);

        //向请求域中添加用户信息
        request.setAttribute("info", claim);

        return true;
    }
}
