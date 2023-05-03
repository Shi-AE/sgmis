package com.AE.sgmis.schedule;

import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.Xtspz;
import com.AE.sgmis.service.PigeonService;
import com.AE.sgmis.service.XtspzService;
import com.AE.sgmis.util.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * 处理图片资源管理定时任务
 */
@Slf4j
@Component
public class PictureSchedule {

    @Autowired
    private XtspzService xtspzService;
    @Autowired
    private PigeonService pigeonService;
    @Autowired
    private FileUtil fileUtil;
    @Value("${file.logo.path}")
    private String logoBasePath;
    @Value("${file.pigeon.path}")
    private String pigeonBasePath;

    /**
     * 删除logo多余图片
     * 每天 3点，12点，19点
     */
    @Scheduled(cron = "0 0 3,12,19 * * *")
    public void deleteLogoPictureTask() {
        //查出所有的图片存放到
        QueryWrapper<Xtspz> wrapper = new QueryWrapper<>();
        wrapper.select("logo_url");
        List<Xtspz> list = xtspzService.list(wrapper);

        //装入set方便查询
        Set<String> logoUrlSet = new HashSet<>();
        list.forEach(xtspz -> {
            if (xtspz != null) {
                logoUrlSet.add(xtspz.getLogoUrl());
            }
        });

        //统计
        AtomicInteger count = new AtomicInteger();

        Path logoPath = Paths.get(logoBasePath);
        Stream<Path> fileList = fileUtil.getFileListByDirectory(logoPath);
        fileList.forEach(file -> {
            String fileName = file.getFileName().toString();
            //判断是否在数据库中
            if (!logoUrlSet.contains(fileName)) {
                //删除
                boolean success = fileUtil.deleteFile(fileName, logoPath);
                if (success) {
                    log.info("成功删除logo图片 {}", fileName);
                    count.getAndIncrement();
                }
            }
        });

        log.info("此次清除logo多余资源图片 {} 个", count);
    }

    /**
     * 删除pigeon多余图片
     * 每天 3点，12点，19点
     */
    @Scheduled(cron = "0 0 3,12,19 * * *")
    public void deletePigeonPictureTask() {
        //查出所有的图片存放到
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.select("picture_url");
        List<Pigeon> list = pigeonService.list(wrapper);

        //装入set方便查询
        Set<String> pigeonUrlSet = new HashSet<>();
        list.forEach(pigeon -> {
            if (pigeon != null) {
                pigeonUrlSet.add(pigeon.getPictureUrl());
            }
        });

        //统计
        AtomicInteger count = new AtomicInteger();

        Path pigeonPath = Paths.get(pigeonBasePath);
        Stream<Path> fileList = fileUtil.getFileListByDirectory(pigeonPath);
        fileList.forEach(file -> {
            String fileName = file.getFileName().toString();
            //判断是否在数据库中
            if (!pigeonUrlSet.contains(fileName)) {
                //删除
                boolean success = fileUtil.deleteFile(fileName, pigeonPath);
                if (success) {
                    log.info("成功删除pigeon多余图片 {}", fileName);
                    count.getAndIncrement();
                }
            }
        });

        log.info("此次清除pigeon多余图片资源 {} 个", count);
    }
}
