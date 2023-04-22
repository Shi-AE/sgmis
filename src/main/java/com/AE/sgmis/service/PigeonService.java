package com.AE.sgmis.service;

import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.PigeonInfo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface PigeonService extends IService<Pigeon> {

    /**
     * 新增鸽子信息通过pigeon里的gid
     * 必须装填gid
     * 如果子代id不为空，需要为子代装填父代id
     */
    Long savePigeon(Pigeon pigeon, PigeonInfo pigeonInfo, Long oid, Long gid);
}