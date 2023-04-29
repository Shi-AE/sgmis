package com.AE.sgmis.service.impl;

import com.AE.sgmis.exceptions.DeleteFailException;
import com.AE.sgmis.exceptions.SaveFailException;
import com.AE.sgmis.mapper.PigeonInfoMapper;
import com.AE.sgmis.mapper.PigeonMapper;
import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.PigeonInfo;
import com.AE.sgmis.service.PigeonService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PigeonServiceImpl extends ServiceImpl<PigeonMapper, Pigeon> implements PigeonService {

    @Autowired
    private PigeonMapper pigeonMapper;
    @Autowired
    private PigeonInfoMapper pigeonInfoMapper;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void updatePigeon(Pigeon pigeon) {
        //条件 id and gid
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.eq("id", pigeon.getId())
                .eq("gid", pigeon.getGid());

        //更新
        int updatePigeon = pigeonMapper.update(pigeon, wrapper);

        if (!SqlHelper.retBool(updatePigeon)) {
            log.error("上传 {} 鸽子数据时，发生服务错误", pigeon);
            throw new SaveFailException("服务错误，更新错误");
        }

        // TODO 保存日志
    }

    @Override
    @Transactional
    public void updatePigeonByTypeAndIds(List<Long> ids, String field, String data, Long gid) {
        //获取当前时间
        LocalDate now = LocalDate.now();

        //装填信息
        Pigeon pigeon = new Pigeon();
        pigeon.setUpdateData(now);

        ids.forEach(id -> {
            UpdateWrapper<Pigeon> wrapper = new UpdateWrapper<>();
            wrapper.eq("id", id).set(field, data);
            int update = pigeonMapper.update(pigeon, wrapper);

            if (!SqlHelper.retBool(update)) {
                throw new SaveFailException("更新失败");
            }
        });

        // TODO 保存日志
    }

    @Override
    @Transactional
    public void deletePigeonById(Long id, String sex, Long gid) {
        //条件 id AND gid
        QueryWrapper<Pigeon> pigeonQueryWrapper = new QueryWrapper<>();
        pigeonQueryWrapper.eq("id", id).eq("gid", gid);

        //删除基础信息
        int deletePigeon = pigeonMapper.delete(pigeonQueryWrapper);

        if (!SqlHelper.retBool(deletePigeon)) {
            throw new DeleteFailException("基础信息删除失败，请重试");
        }

        //条件 pid = id
        QueryWrapper<PigeonInfo> pigeonInfoQueryWrapper = new QueryWrapper<>();
        pigeonInfoQueryWrapper.eq("pid", id);

        //删除额外信息
        int deletePigeonInfo = pigeonInfoMapper.delete(pigeonInfoQueryWrapper);

        if (!SqlHelper.retBool(deletePigeonInfo)) {
            log.error("id = {} 时，额外信息删除失败", id);
            throw new DeleteFailException("额外信息删除失败，请重试");
        }

        if (sex.equals("雄")) {
            //条件 fid = id and gid
            UpdateWrapper<Pigeon> wrapper = new UpdateWrapper<>();
            wrapper.eq("fid", id)
                    .eq("gid", gid)
                    .set("fid", null);
            pigeonMapper.update(null, wrapper);
        } else if (sex.equals("雌")) {
            //条件 mid = id and gid
            UpdateWrapper<Pigeon> wrapper = new UpdateWrapper<>();
            wrapper.eq("mid", id)
                    .eq("gid", gid)
                    .set("mid", null);
            pigeonMapper.update(null, wrapper);
        } else {
            throw new DeleteFailException("信息出错，请重试");
        }

        // TODO 记录日志
    }

    @Override
    @Transactional
    public void deletePigeonByIds(List<Pigeon> pigeons, Long gid) {
        pigeons.forEach(pigeon -> {
            //检查数据完整
            if (pigeon.getId() == null || pigeon.getSex() == null) {
                throw new SaveFailException("提交数据不完整");
            }
            deletePigeonById(pigeon.getId(), pigeon.getSex(), gid);
        });

        // TODO 记录日志
    }

    @Override
    @Transactional
    public void sharePigeon(List<Long> ids, Long receiveGid, Long gid) {
        int n = ids.size();

        //根据ids获取所有数据
        List<Pigeon> pigeons = pigeonMapper.selectBatchIds(ids);
        List<PigeonInfo> pigeonInfos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            QueryWrapper<PigeonInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("pid", ids.get(i));
            pigeonInfos.add(pigeonInfoMapper.selectOne(wrapper));
        }

        //获取日期
        LocalDate now = LocalDate.now();

        //处理数据，并插入
        for (int i = 0; i < n; i++) {
            //pigeon
            //清除 id, fid, mid
            //修改 update_data, gid
            Pigeon pigeon = pigeons.get(i);
            //清除
            pigeon.setId(null);
            pigeon.setFid(null);
            pigeon.setMid(null);
            //修改
            pigeon.setUpdateData(now);
            pigeon.setGid(receiveGid);
            //插入
            int insertPigeon = pigeonMapper.insert(pigeon);
            //检查
            if (!SqlHelper.retBool(insertPigeon)) {
                throw new SaveFailException("分享基础信息失败");
            }

            //pigeonInfo
            //清除 id
            //修改 create_time, pid
            PigeonInfo pigeonInfo = pigeonInfos.get(i);
            //清除
            pigeonInfo.setId(null);
            //修改
            pigeonInfo.setCreateTime(now);
            pigeonInfo.setPid(pigeon.getId());
            //插入
            int insertPigeonInfo = pigeonInfoMapper.insert(pigeonInfo);
            //检查
            if (!SqlHelper.retBool(insertPigeonInfo)) {
                throw new SaveFailException("额外基础信息失败");
            }

            // TODO 记录日志
        }
    }

    @Override
    @Transactional
    public void relatePigeon(Long id, String sex, Long oid, Long gid) {
        //获取更新日期
        LocalDate now = LocalDate.now();

        //条件
        UpdateWrapper<Pigeon> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", oid)
                .eq("gid", gid)
                .set("update_data", now)
                .set(sex.equals("雄"), "fid", id)
                .set(sex.equals("雌"), "mid", id);

        int update = pigeonMapper.update(null, wrapper);

        if (!SqlHelper.retBool(update)) {
            log.error("关联 id = {}, oid = {} 时发生错误", id, oid);
            throw new SaveFailException("关联失败");
        }

        // TODO 记录日志
    }

    @Override
    @Transactional
    public void rapidBatchAddPigeon(List<Pigeon> pigeons, List<PigeonInfo> pigeonInfos) {

        for (int i = 0, pigeonsSize = pigeons.size(); i < pigeonsSize; i++) {
            Pigeon pigeon = pigeons.get(i);

            //新增
            int insert = pigeonMapper.insert(pigeon);

            //检查
            if (!SqlHelper.retBool(insert)) {
                throw new SaveFailException("保存鸽子基础信息失败");
            }

            //装填pid
            pigeonInfos.get(i).setPid(pigeon.getId());
        }

        pigeonInfos.forEach(pigeonInfo -> {
            int insert = pigeonInfoMapper.insert(pigeonInfo);

            //检查
            if (!SqlHelper.retBool(insert)) {
                throw new SaveFailException("保存鸽子额外信息失败");
            }
        });


        // TODO 记录日志
    }
}