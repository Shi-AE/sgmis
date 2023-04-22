package com.AE.sgmis.service.impl;

import com.AE.sgmis.exceptions.SaveFailException;
import com.AE.sgmis.mapper.PigeonInfoMapper;
import com.AE.sgmis.mapper.PigeonMapper;
import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.PigeonInfo;
import com.AE.sgmis.service.PigeonService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PigeonServiceImpl extends ServiceImpl<PigeonMapper, Pigeon> implements PigeonService {

    @Autowired
    private PigeonMapper pigeonMapper;
    @Autowired
    private PigeonInfoMapper pigeonInfoMapper;

    @Override
    public Long savePigeon(Pigeon pigeon, PigeonInfo pigeonInfo, Long oid, Long gid) {
        //获取当前时间
        LocalDate now = LocalDate.now();

        //装填更新时间
        pigeon.setUpdateData(now);

        //pid
        Long pid = pigeon.getId();

        //判断此记录是否存在
        if (pid == null) {
            //不存在，新增
            //装填创建时间
            pigeonInfo.setCreateTime(now);

            //装填gid
            pigeon.setGid(gid);

            //执行新增
            int insertPigeon = pigeonMapper.insert(pigeon);

            //检查新增
            if (!SqlHelper.retBool(insertPigeon)) {
                throw new SaveFailException("新增鸽子基础信息时失败");
            }

            //获取pid
            pid = pigeon.getId();

            //装填pid
            pigeonInfo.setPid(pid);

            //执行新增
            int insertPigeonInfo = pigeonInfoMapper.insert(pigeonInfo);

            //检查新增
            if (!SqlHelper.retBool(insertPigeonInfo)) {
                throw new SaveFailException("新增鸽子额外信息时失败");
            }
        } else {
            //存在，更新
            //条件 gid = gid and id = id
            QueryWrapper<Pigeon> pigeonWrapper = new QueryWrapper<>();
            pigeonWrapper.eq("gid", gid).eq("id", pid);

            //执行更新
            int pigeonUpdate = pigeonMapper.update(pigeon, pigeonWrapper);

            //检查更新
            if (!SqlHelper.retBool(pigeonUpdate)) {
                throw new SaveFailException("更新鸽子基础信息时失败");
            }

            //条件 pid = pid
            QueryWrapper<PigeonInfo> pigeonInfoWrapper = new QueryWrapper<>();
            pigeonInfoWrapper.eq("pid", pid);

            //执行更新
            int updatePigeonInfo = pigeonInfoMapper.update(pigeonInfo, pigeonInfoWrapper);

            //检查更新
            if (!SqlHelper.retBool(updatePigeonInfo)) {
                throw new SaveFailException("更新鸽子基额外息时失败");
            }
        }

        //为此鸽子的子代更新父代id
        if (oid != null) {
            //如果oid不为空则查子代
            Pigeon offspringPigeon = pigeonMapper.selectById(oid);

            //执行对应性别装填
            if (pigeon.getSex().equals("雄")) {
                offspringPigeon.setFid(pid);
            } else if (pigeon.getSex().equals("雌")) {
                offspringPigeon.setMid(pid);
            }

            //执行更新
            int offspringPigeonUpdate = pigeonMapper.updateById(offspringPigeon);

            //检查跟新
            if (!SqlHelper.retBool(offspringPigeonUpdate)) {
                throw new SaveFailException("鸽子子代信息更新失败");
            }
        }

        // TODO 保存日志
        return pid;
    }
}