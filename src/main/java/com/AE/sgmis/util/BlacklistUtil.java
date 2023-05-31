package com.AE.sgmis.util;

import com.AE.sgmis.result.RedisNamespace;
import com.AE.sgmis.result.SeverityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlacklistUtil {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据级别向黑名单中添加ip
     * 并设置过期时间
     */
    public void addForbiddenIp(String ip, Object message, SeverityLevel level) {
        redisUtil.set(RedisNamespace.Blacklist, ip, message, level.getExpire());
    }

    /**
     * 通过ip获取黑名单中的信息
     */
    public Object getForbiddenInfo(String ip) {
        return redisUtil.get(RedisNamespace.Blacklist, ip);
    }
}
