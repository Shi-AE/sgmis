package com.AE.sgmis.service.impl;

import com.AE.sgmis.exceptions.SaveFailException;
import com.AE.sgmis.mapper.PigeonGpcxMapper;
import com.AE.sgmis.mapper.PigeonMapper;
import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.PigeonGpcx;
import com.AE.sgmis.service.PigeonGpcxService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PigeonGpcxServiceImpl extends ServiceImpl<PigeonGpcxMapper, PigeonGpcx> implements PigeonGpcxService {

    @Autowired
    private PigeonGpcxMapper pigeonGpcxMapper;
    @Autowired
    private PigeonMapper pigeonMapper;

    @Override
    @Transactional
    public int addPigeonToGpcx(List<Long> ids, Long gpcxId, String name) {

        AtomicReference<Integer> updateNumber = new AtomicReference<>(0);
        //获取更新时间
        LocalDate now = LocalDate.now();

        ids.forEach(id -> {
            UpdateWrapper<PigeonGpcx> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("pid", id).set("gpcx_id", gpcxId);
            //更新
            int update = pigeonGpcxMapper.update(null, updateWrapper);

            if (!SqlHelper.retBool(update)) {
                //新增
                PigeonGpcx pigeonGpcx = new PigeonGpcx();
                pigeonGpcx.setPid(id);
                pigeonGpcx.setGpcxId(gpcxId);
                int insert = pigeonGpcxMapper.insert(pigeonGpcx);

                if (!SqlHelper.retBool(insert)) {
                    throw new SaveFailException("新增时发生错误");
                }

                // TODO 记录新增日志
            } else {
                //记录更新数
                updateNumber.getAndSet(updateNumber.get() + 1);

                // TODO 记录更新日志
            }
        });

        //更新鸽子信息
        //条件 id = ids 设置 更新 时间 和 鸽棚名
        UpdateWrapper<Pigeon> wrapper = new UpdateWrapper<>();
        wrapper.in("id", ids)
                .set("gpcx", name)
                .set("update_data", now);
        int update = pigeonMapper.update(null, wrapper);
        if (!SqlHelper.retBool(update)) {
            throw new SaveFailException("更新鸽子信息失败，请重试");
        }

        return updateNumber.get();
    }
}
