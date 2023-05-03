package com.AE.sgmis.service;

import com.AE.sgmis.pojo.Oplog;
import com.AE.sgmis.result.LogType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OplogService extends IService<Oplog> {

    /**
     * 记录日志
     * 接收所有信息
     *
     * @param account    操作者
     * @param ringNumber 足环
     * @param pid        鸽子id
     * @param gid        组id
     * @param type       类型
     */
    void oplog(String account, String ringNumber, Long pid, Long gid, LogType type);

    /**
     * 记录日志
     * 接收所有信息
     * 接收更多提示
     *
     * @param account    操作者
     * @param ringNumber 足环
     * @param pid        鸽子id
     * @param gid        组id
     * @param tip        额外提示
     * @param type       类型
     */
    void oplog(String account, String ringNumber, Long pid, Long gid, String tip, LogType type);

    /**
     * 记录日志
     * 接收pid
     *
     * @param account 操作者
     * @param pid     鸽子id
     * @param gid     组id
     * @param type    类型
     */
    void oplog(String account, Long pid, Long gid, LogType type);

    /**
     * 记录日志
     * 接收pid
     *
     * @param account 操作者
     * @param pid     鸽子id
     * @param gid     组id
     * @param tip     额外提示
     * @param type    类型
     */
    void oplog(String account, Long pid, Long gid, String tip, LogType type);

    /**
     * 记录日志
     * 接收足环
     * 仅接受删除类型
     *
     * @param account    操作者
     * @param ringNumber 足环
     * @param gid        组id
     * @param type       类型
     */
    void oplog(String account, String ringNumber, Long gid, LogType type);

    /**
     * 批量插入自定义日志
     */
    void batchAddLog(List<Oplog> oplogs, Long gid, String account);
}