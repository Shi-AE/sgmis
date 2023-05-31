package com.AE.sgmis.util;

import com.AE.sgmis.result.RedisNamespace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisConnectionCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具
 */
@Slf4j
@Component
public class RedisUtil {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 发送ping命令
     * 检查数据库连接
     */
    public boolean PING() {
        try {
            String execute = redisTemplate.execute(RedisConnectionCommands::ping);
            if (execute == null || !execute.equals("PONG")) {
                log.error("redis PING 失败 {}}", execute);
                return false;
            }
        } catch (RedisConnectionFailureException e) {
            log.error("redis连接错误", e);
            return false;
        }
        return true;
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param namespace 命名空间
     * @param key       键
     * @param value     值
     * @param time      时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public void set(RedisNamespace namespace, String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(namespace.getValue() + key, value, time, TimeUnit.SECONDS);
        } else {
            redisTemplate.opsForValue().set(namespace.getValue() + key, value);
        }
    }

    /**
     * 无命名空间
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public void set(String key, Object value, long time) {
        set(RedisNamespace.Ancestor, key, value, time);
    }

    /**
     * 带父级命名空间
     * 获取命名空间下的所有key
     */
    public Set<String> keys(RedisNamespace redisNamespace, Object namespace) {
        return redisTemplate.keys(redisNamespace.getValue() + namespace + "*");
    }

    /**
     * 带父级命名空间
     * 获取命名空间下的所有key
     */
    public Set<String> keys(RedisNamespace redisNamespace) {
        return redisTemplate.keys(redisNamespace.getValue() + "*");
    }

    /**
     * 带命名空间
     * 查询过期时间
     *
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(RedisNamespace namespace, String key) {
        return redisTemplate.getExpire(namespace.getValue() + key);
    }

    /**
     * 无命名空间
     * 查询过期时间
     *
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(String key) {
        return getExpire(RedisNamespace.Ancestor, key);
    }

    /**
     * 带命名空间
     * 删除缓存
     */
    public void del(RedisNamespace namespace, String key) {
        redisTemplate.delete(namespace.getValue() + key);
    }

    /**
     * 无命名空间
     * 删除缓存
     */
    public void del(String key) {
        del(RedisNamespace.Ancestor, key);
    }

    /**
     * 带命名空间
     * 获取普通缓存
     */
    public Object get(RedisNamespace namespace, String key) {
        return key == null ? null : redisTemplate.opsForValue().get(namespace.getValue() + key);
    }

    /**
     * 带命名空间
     * 指定缓存失效时间
     */
    public void expire(RedisNamespace namespace, String key, long time) {
        if (time > 0) {
            redisTemplate.expire(namespace.getValue() + key, time, TimeUnit.SECONDS);
        }
    }
}
