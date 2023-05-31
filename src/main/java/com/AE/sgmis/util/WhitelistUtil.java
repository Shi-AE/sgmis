package com.AE.sgmis.util;

import com.AE.sgmis.result.RedisNamespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 白名单工具类
 */
@Component
public class WhitelistUtil {

    @Autowired
    private RedisUtil redisUtil;
    @Value("${jwt.expires}")
    private int expires;
    @Value("${jwt.ipLimit}")
    private int ipLimit;
    @Value("${jwt.update-gap}")
    private Integer updateGap;

    /**
     * 将token存入白名单
     */
    public void setToken(Long id, String ip, String token) {
        redisUtil.set(
                RedisNamespace.Whitelist,
                id + ":" + ip,
                token,
                expires
        );
    }

    /**
     * 限制单用户id的在线设备数
     * 如果存在超出限制的设备数
     * 则将其token踢下线
     */
    public void limitToken(Long id) {
        //获取此id下的所有key
        Set<String> keys = redisUtil.keys(RedisNamespace.Whitelist, id + ":");

        //如果key个数小于限制则放行
        if (keys.size() <= ipLimit) {
            return;
        }

        //删除距离过期时间短的token

        //查询出key对应的过期时间，放入优先队列
        //Queue元素为数组，e[0]key，e[1]为过期时间
        Queue<Object[]> expiresQueue = new PriorityQueue<>(Comparator.comparingLong(e -> (Long) e[1]));
        keys.forEach(key -> expiresQueue.add(new Object[]{key, redisUtil.getExpire(key)}));

        while (expiresQueue.size() > ipLimit) {
            Object[] poll = expiresQueue.poll();
            assert poll != null;
            redisUtil.del((String) poll[0]);
        }
    }

    /**
     * 验证白名单中的token
     */
    public boolean verifyToken(Long id, String ip, String token) {
        //获取token
        Object redisToken = redisUtil.get(RedisNamespace.Whitelist, id + ":" + ip);
        if (redisToken == null) {
            return false;
        }
        return redisToken.equals(token);
    }

    /**
     * 重置redis过期时间
     */
    public void updateExpires(Long id, String ip) {
        Long redisExpires = redisUtil.getExpire(RedisNamespace.Whitelist, id + ":" + ip);
        if (expires - redisExpires > updateGap) {
            redisUtil.expire(RedisNamespace.Whitelist, id + ":" + ip, expires);
        }
    }

    /**
     * 统计活跃人数
     */
    public long getActive(RedisNamespace namespace, String key) {
        //根据获取包含key的key
        Set<String> keys = redisUtil.keys(namespace, key);

        AtomicLong count = new AtomicLong();

        //筛选并统计最近活跃的人数
        keys.forEach(k -> {
            Long tokenExpires = redisUtil.getExpire(k);

            if (expires - tokenExpires < updateGap) {
                count.getAndIncrement();
            }
        });

        return count.get();
    }

    /**
     * 将用户的所有设备
     * 从白名单中剔除
     */
    public void deleteToken(Long id) {
        Set<String> keys = redisUtil.keys(RedisNamespace.Whitelist, id + ":");
        //删除所有设备
        keys.forEach(key -> redisUtil.del(key));
    }

    /**
     * 查看用户的活跃状态
     */
    public int getState(RedisNamespace namespace, String key) {
        //根据获取包含key的key
        Set<String> keys = redisUtil.keys(namespace, key);

        int state = 0;

        //查看每个设备的活跃状态
        for (String k : keys) {
            Long tokenExpires = redisUtil.getExpire(k);

            //如果满足条件，将状态设为最高在线状态
            if (expires - tokenExpires < updateGap) {
                state = 2;
                break;
            }

            //如果满足条件设置此状态
            if (state < 1 && tokenExpires > 0) {
                state = 1;
            }
        }

        return state;
    }
}
