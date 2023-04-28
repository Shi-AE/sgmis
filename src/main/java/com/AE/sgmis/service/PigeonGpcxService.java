package com.AE.sgmis.service;

import com.AE.sgmis.pojo.PigeonGpcx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PigeonGpcxService extends IService<PigeonGpcx> {

    /**
     * 将鸽子添加到鸽棚巢箱
     * 返回更新数量
     * 记录日志
     */
    int addPigeonToGpcx(List<Long> ids, Long gpcxId, String name);
}
