package com.AE.sgmis.schedule;

import com.AE.sgmis.pojo.Oplog;
import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.service.OplogService;
import com.AE.sgmis.service.PigeonService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class OplogSchedule {

    @Autowired
    private OplogService oplogService;
    @Autowired
    private PigeonService pigeonService;

    /**
     * 定时为操作日志补充足环
     */
    @Scheduled(cron = "0 0 3,12,19 * * *")
    public void replenishRingNumber() {
        //select pid
        //from t_oplog
        //where pid is not null
        //and ring_number is null
        //group by pid
        QueryWrapper<Oplog> oplogQueryWrapper = new QueryWrapper<>();
        oplogQueryWrapper.select("pid")
                .isNotNull("pid")
                .isNull("ring_number")
                .groupBy("pid");

        List<Oplog> oplogList = oplogService.list(oplogQueryWrapper);

        //计数
        AtomicInteger count = new AtomicInteger();

        oplogList.forEach(oplog -> {
            Long pid = oplog.getPid();

            //根据pid查询ringNumber
            Pigeon pigeon = pigeonService.getById(pid);
            if (pigeon == null) {
                return;
            }
            String ringNumber = pigeon.getRingNumber();

            //更新oplog的ringNumber
            int update = oplogService.updateRingNUmber(pid, ringNumber);

            count.addAndGet(update);
        });

        log.info("成功补充日志ringNumber {} 条", count);
    }
}
