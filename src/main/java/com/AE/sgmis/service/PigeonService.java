package com.AE.sgmis.service;

import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.PigeonInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PigeonService extends IService<Pigeon> {

    /**
     * 新增鸽子信息通过pigeon里的gid
     * 必须装填gid
     * 如果子代id不为空，需要为子代装填父代id
     * 记录日志
     */
    Long savePigeon(Pigeon pigeon, PigeonInfo pigeonInfo, Long oid, Long gid);

    /**
     * 根据id
     * 整体更新鸽子信息
     * 记录日志
     */
    void updatePigeon(Pigeon pigeon);

    /**
     * 根据 字段类型 和 id列表 更新对应内容
     * 记录更新时间
     * 记录日志
     */
    void updatePigeonByTypeAndIds(List<Long> ids, String field, String data, Long gid);

    /**
     * 根据id删除鸽子
     * 根据性别删除子代关系
     * 记录日志
     */
    void deletePigeonById(Long id, String sex, Long gid);

    /**
     * 根据pigeons批量删除鸽子
     * 根据性别删除子代关系
     * 记录日志
     */
    void deletePigeonByIds(List<Pigeon> pigeons, Long gid);

    /**
     * 批量分享鸽子血统信息
     * 分享者记录日志
     * 接收者记录日志
     */
    void sharePigeon(List<Long> ids, Long receiveGid, Long gid);

    /**
     * 关联血亲关系
     * 根据id、sex、oid
     * 子代记录日志
     */
    void relatePigeon(Long id, String sex, Long oid, Long gid);

    /**
     * 快速批量入库子代
     * 记录日志
     */
    void rapidBatchAddPigeon(List<Pigeon> pigeons, List<PigeonInfo> pigeonInfos);
}