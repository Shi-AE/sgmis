package com.AE.sgmis.schedule;

import com.AE.sgmis.service.LoginMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogSchedule {

    @Autowired
    private LoginMsgService loginMsgService;

    /**
     * 定时清理登录日志信息
     */
    @Scheduled(cron = "0 0 3 * * *")
    public void loginLogTask() {

        int remove = loginMsgService.removeForCountTask();

        log.info("成功清理 {} 条过期登录信息记录", remove);
    }
}
