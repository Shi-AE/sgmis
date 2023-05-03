package com.AE.sgmis.service.impl;

import com.AE.sgmis.exceptions.LogException;
import com.AE.sgmis.mapper.OplogMapper;
import com.AE.sgmis.pojo.Oplog;
import com.AE.sgmis.result.LogType;
import com.AE.sgmis.service.OplogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements OplogService {

    @Autowired
    private OplogMapper oplogMapper;

    @Override
    @Transactional
    public void oplog(String account, String ringNumber, Long pid, Long gid, LogType type) {
        Oplog oplog = new Oplog(
                account, ringNumber, type.getIndex(),
                type.getTip(), LocalDateTime.now(), pid, gid
        );
        int insert = oplogMapper.insert(oplog);
        if (!SqlHelper.retBool(insert)) {
            log.error("{} 日志记录失败", oplog);
            throw new LogException("日志保存失败");
        }
    }

    @Override
    public void oplog(String account, String ringNumber, Long pid, Long gid, String tip, LogType type) {
        Oplog oplog = new Oplog(
                account, ringNumber, type.getIndex(),
                type.getTip() + "：" + tip, LocalDateTime.now(), pid, gid
        );
        int insert = oplogMapper.insert(oplog);
        if (!SqlHelper.retBool(insert)) {
            log.error("{} 日志记录失败", oplog);
            throw new LogException("日志保存失败");
        }
    }

    @Override
    @Transactional
    public void oplog(String account, Long pid, Long gid, LogType type) {
        Oplog oplog = new Oplog(
                account, type.getIndex(), type.getTip(),
                LocalDateTime.now(), pid, gid
        );
        int insert = oplogMapper.insert(oplog);
        if (!SqlHelper.retBool(insert)) {
            log.error("{} 日志记录失败", oplog);
            throw new LogException("日志保存失败");
        }
    }

    @Override
    public void oplog(String account, Long pid, Long gid, String tip, LogType type) {
        Oplog oplog = new Oplog(
                account, type.getIndex(), type.getTip() + "：" + tip,
                LocalDateTime.now(), pid, gid
        );
        int insert = oplogMapper.insert(oplog);
        if (!SqlHelper.retBool(insert)) {
            log.error("{} 日志记录失败", oplog);
            throw new LogException("日志保存失败");
        }
    }

    @Override
    @Transactional
    public void oplog(String account, String ringNumber, Long gid, LogType type) {
        if (type != LogType.DELETE) {
            throw new LogException("日志类型错误");
        }
        Oplog oplog = new Oplog(
                account, ringNumber, type.getIndex(),
                type.getTip(), LocalDateTime.now(), gid
        );
        int insert = oplogMapper.insert(oplog);
        if (!SqlHelper.retBool(insert)) {
            log.error("{} 日志记录失败", oplog);
            throw new LogException("日志保存失败");
        }
    }

    @Override
    @Transactional
    public void batchAddLog(List<Oplog> oplogs, Long gid, String account) {

        //获取时间
        LocalDateTime now = LocalDateTime.now();

        oplogs.forEach(oplog -> {
            oplog.setGid(gid);
            oplog.setAuthor(account);
            oplog.setTime(now);
            oplog.setContent(LogType.OTHER.getIndex());
            int insert = oplogMapper.insert(oplog);
            if (!SqlHelper.retBool(insert)) {
                log.error("{} 日志记录失败", oplog);
                throw new LogException("日志保存失败");
            }
        });
    }
}