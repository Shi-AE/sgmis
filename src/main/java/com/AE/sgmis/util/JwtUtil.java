package com.AE.sgmis.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * token生成校验工具
 */
@Component
public class JwtUtil {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expires}")
    private int expires;

    /**
     * 获取token
     *
     * @return token值
     */
    public String getToken(String account) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, expires);
        return JWT.create()
                .withClaim("account", account)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(secretKey));
    }

    /**
     * 验证token
     *
     * @return 验证信息
     */
    public DecodedJWT verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        return jwtVerifier.verify(token);
    }
}
