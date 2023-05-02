package com.AE.sgmis.service;

import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.PigeonInfo;
import com.AE.sgmis.pojo.PigeonWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface PigeonService extends IService<Pigeon> {

    /**
     * 接收一条鸽子记录
     * 如果有鸽子有id信息则根据id更新
     * 如果没有id则新增
     * 同时可选择的为其更新子代信息
     * 如果子代id不为空
     * 需要为子代装填父代id
     * 记录日志
     */
    Long saveOrUpdatePigeonById(Pigeon pigeon, PigeonInfo pigeonInfo, Long oid, Long gid, String account);

    /**
     * 根据id
     * 整体更新鸽子基础信息
     * 记录日志
     */
    void updatePigeon(Pigeon pigeon, String account);

    /**
     * 根据 字段类型 和 id列表 更新对应内容
     * 记录更新时间
     * 记录日志
     */
    void updatePigeonByTypeAndIds(List<Long> ids, String field, String data, Long gid, String account);

    /**
     * 根据id删除鸽子
     * 根据性别删除子代关系
     * 记录日志
     */
    void deletePigeonById(Long id, String sex, Long gid, String account);

    /**
     * 根据pigeons批量删除鸽子
     * 根据性别删除子代关系
     * 记录日志
     */
    void deletePigeonByIds(List<Pigeon> pigeons, Long gid, String account);

    /**
     * 批量分享鸽子血统信息
     * 分享者记录日志
     * 接收者记录日志
     */
    void sharePigeon(List<Long> ids, Long receiveGid, Long gid, String account);

    /**
     * 关联血亲关系
     * 根据id、sex、oid
     * 子代记录日志
     */
    void relatePigeon(Long id, String sex, String ringNumber, Long oid, Long gid, String account);

    /**
     * 快速批量入库子代
     * 记录日志
     */
    void rapidBatchAddPigeon(List<Pigeon> pigeons, List<PigeonInfo> pigeonInfos, Long gid, String account, String fatherRingNumber, String motherRingNumber);

    /**
     * 根据文件解析结果保存鸽子
     * 记录日志
     * 父代以足环为主导
     */
    void savePigeonByFile(List<Map<String, PigeonWrapper>> pigeonWrappers, Long gid, String account);
}