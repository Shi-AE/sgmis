package com.AE.sgmis.service.impl;

import com.AE.sgmis.exceptions.SaveFailException;
import com.AE.sgmis.mapper.GpcxMapper;
import com.AE.sgmis.mapper.PigeonGpcxMapper;
import com.AE.sgmis.pojo.Gpcx;
import com.AE.sgmis.pojo.PigeonGpcx;
import com.AE.sgmis.service.GpcxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Slf4j
@Service
public class GpcxServiceImpl extends ServiceImpl<GpcxMapper, Gpcx> implements GpcxService {

    @Autowired
    private GpcxMapper gpcxMapper;
    @Autowired
    private PigeonGpcxMapper pigeonGpcxMapper;

    @Transactional
    public int gpcxRemoveById(Serializable id) {
        //删除鸽棚巢箱中的鸽子
        //条件 gpcxId = gpcxId
        QueryWrapper<PigeonGpcx> pigeonGpcxWrapper = new QueryWrapper<>();
        pigeonGpcxWrapper.eq("gpcx_id", id);
        int pigeonDelete = pigeonGpcxMapper.delete(pigeonGpcxWrapper);
        //删除鸽棚巢箱
        //条件 id = gpcxId
        int gpcxDelete = gpcxMapper.deleteById(id);
        if (!SqlHelper.retBool(gpcxDelete)) {
            log.error("删除 {} 时，删除鸽棚巢箱失败", id);
            throw new SaveFailException("服务错误，删除失败");
        }
        return pigeonDelete;
    }
}
