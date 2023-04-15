package com.AE.sgmis.service;

import com.AE.sgmis.pojo.Gpcx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

public interface GpcxService extends IService<Gpcx> {

    /**
     * 删除鸽棚巢箱业务
     */
    int gpcxRemoveById(Serializable id);
}
