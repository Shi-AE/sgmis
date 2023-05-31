package com.AE.sgmis.schedule;

import com.AE.sgmis.result.RedisNamespace;
import com.AE.sgmis.util.RedisUtil;
import com.AE.sgmis.util.WhitelistUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OnlineSchedule {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private WhitelistUtil whitelistUtil;

    /**
     * 固定时间间隔
     * 从上次执行完算起
     * 每个十分钟统计在线人数
     */
    @Scheduled(fixedDelay = 600000, initialDelay = 1000)
    public void countOnline() {
        //获取白名单中的所有活跃人数
        long active = whitelistUtil.getActive(RedisNamespace.Whitelist, "");

        //将统计的人数存入缓存，不设置过期
        redisUtil.set(RedisNamespace.Online, "", active, -1);

        log.info("统计当前活跃人数 {}", active);
    }
}
