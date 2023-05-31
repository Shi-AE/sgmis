package com.AE.sgmis.service.impl;

import com.AE.sgmis.exceptions.DeleteFailException;
import com.AE.sgmis.exceptions.NotFoundException;
import com.AE.sgmis.exceptions.SaveFailException;
import com.AE.sgmis.mapper.PigeonInfoMapper;
import com.AE.sgmis.mapper.PigeonMapper;
import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.PigeonInfo;
import com.AE.sgmis.pojo.vo.PigeonWrapperVo;
import com.AE.sgmis.result.LogType;
import com.AE.sgmis.service.OplogService;
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
import java.util.Map;

@Slf4j
@Service
public class PigeonServiceImpl extends ServiceImpl<PigeonMapper, Pigeon> implements PigeonService {

    @Autowired
    private PigeonMapper pigeonMapper;
    @Autowired
    private PigeonInfoMapper pigeonInfoMapper;
    @Autowired
    private OplogService oplogService;

    @Override
    @Transactional
    public Long saveOrUpdatePigeonById(Pigeon pigeon, PigeonInfo pigeonInfo, Long oid, Long gid, String account) {
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

            //记录日志
            oplogService.oplog(account, pigeon.getRingNumber(), pid, gid, LogType.INSERT);
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

            //记录日志
            oplogService.oplog(account, pigeon.getRingNumber(), pid, gid, LogType.UPDATE);
        }

        //为此鸽子的子代更新父代id
        if (oid != null) {
            //条件
            UpdateWrapper<Pigeon> wrapper = new UpdateWrapper<>();
            wrapper.eq("id", oid)
                    .eq("gid", gid)
                    .set("update_data", now)
                    .set(pigeon.getSex().equals("雄"), "fid", pid)
                    .set(pigeon.getSex().equals("雌"), "mid", pid);

            int offspringPigeonUpdate = pigeonMapper.update(null, wrapper);

            //检查跟新
            if (!SqlHelper.retBool(offspringPigeonUpdate)) {
                throw new SaveFailException("鸽子子代信息更新失败");
            }

            //记录日志
            oplogService.oplog(account, oid, gid, pigeon.getRingNumber(), LogType.RELATE);
        }

        return pid;
    }

    @Override
    @Transactional
    public void updatePigeon(Pigeon pigeon, String account) {
        Long id = pigeon.getId();
        Long gid = pigeon.getGid();
        //条件 id and gid
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id)
                .eq("gid", gid);

        //更新
        int updatePigeon = pigeonMapper.update(pigeon, wrapper);

        if (!SqlHelper.retBool(updatePigeon)) {
            log.error("上传 {} 鸽子数据时，发生服务错误", pigeon);
            throw new SaveFailException("服务错误，更新错误");
        }

        //记录日志
        oplogService.oplog(account, pigeon.getRingNumber(), id, gid, LogType.UPDATE);
    }

    @Override
    @Transactional
    public void updatePigeonByTypeAndIds(List<Long> ids, String field, String data, Long gid, String account) {
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

            //记录日志
            oplogService.oplog(account, id, gid, data, LogType.UPDATE);
        });
    }

    @Override
    @Transactional
    public void deletePigeonById(Long id, String sex, Long gid, String account) {
        //条件 id AND gid
        QueryWrapper<Pigeon> pigeonQueryWrapper = new QueryWrapper<>();
        pigeonQueryWrapper.eq("id", id).eq("gid", gid);

        //获取信息以记录日志
        Pigeon pigeon = pigeonMapper.selectOne(pigeonQueryWrapper);

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

        //记录日志
        oplogService.oplog(account, pigeon.getRingNumber(), gid, LogType.DELETE);

        //更新子代的父代信息
        //因为要记录日志所以先检索所有要更新的数据
        QueryWrapper<Pigeon> queryWrapper = new QueryWrapper<>();
        //条件
        queryWrapper.eq("gid", gid)
                .eq(sex.equals("雌"), "mid", id)
                .eq(sex.equals("雄"), "fid", id);
        List<Pigeon> offspringPigeons = pigeonMapper.selectList(queryWrapper);

        //获取更新时间
        LocalDate now = LocalDate.now();

        //更新
        offspringPigeons.forEach(offspringPigeon -> {
            UpdateWrapper<Pigeon> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", offspringPigeon.getId())
                    .set("update_data", now)
                    .set(sex.equals("雄"), "fid", null)
                    .set(sex.equals("雌"), "mid", null);

            int update = pigeonMapper.update(null, updateWrapper);

            if (!SqlHelper.retBool(update)) {
                throw new SaveFailException("解除血亲关系出错");
            }

            //记录日志
            oplogService.oplog(account, offspringPigeon.getRingNumber(), offspringPigeon.getId(), gid, LogType.UNPARENT);
        });
    }

    @Override
    @Transactional
    public void deletePigeonByIds(List<Pigeon> pigeons, Long gid, String account) {
        pigeons.forEach(pigeon -> {
            //检查数据完整
            Long id = pigeon.getId();
            String sex = pigeon.getSex();
            if (id == null || sex == null || !(sex.equals("雄") || sex.equals("雌"))) {
                throw new SaveFailException("提交数据不完整");
            }

            deletePigeonById(id, sex, gid, account);
        });
    }

    @Override
    @Transactional
    public void sharePigeon(List<Long> ids, Long receiveGid, Long gid, String account) {
        //根据ids获取所有数据
        //条件 gid and id in ids
        QueryWrapper<Pigeon> pigeonWrapper = new QueryWrapper<>();
        pigeonWrapper.eq("gid", gid).in("id", ids);
        List<Pigeon> pigeons = pigeonMapper.selectList(pigeonWrapper);

        //检查数据个数是否准确
        int n = ids.size();
        if (n != pigeons.size()) {
            throw new NotFoundException("数据获取错误");
        }

        //同时获取额外信息，一一对应
        List<PigeonInfo> pigeonInfos = new ArrayList<>();
        pigeons.forEach(pigeon -> {
            QueryWrapper<PigeonInfo> pigeonInfoWrapper = new QueryWrapper<>();
            pigeonInfoWrapper.eq("pid", pigeon.getId());
            PigeonInfo pigeonInfo = pigeonInfoMapper.selectOne(pigeonInfoWrapper);
            //检查
            if (pigeonInfo == null) {
                throw new NotFoundException("数据获取错误");
            }
            pigeonInfos.add(pigeonInfo);
        });

        //获取日期
        LocalDate now = LocalDate.now();

        //处理数据，并插入
        for (int i = 0; i < n; i++) {
            Pigeon pigeon = pigeons.get(i);
            //发送者记录日志
            oplogService.oplog(account, pigeon.getRingNumber(), pigeon.getId(), gid, LogType.SHARE);

            //pigeon
            //清除 id, fid, mid
            //修改 update_data, gid
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

            //接收者记录日志
            oplogService.oplog(account, pigeon.getRingNumber(), pigeon.getId(), receiveGid, LogType.RECEIVE);
        }
    }

    @Override
    @Transactional
    public void relatePigeon(Long id, String sex, String ringNumber, Long oid, Long gid, String account) {
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

        //记录日志
        oplogService.oplog(account, oid, gid, ringNumber, LogType.RELATE);
    }

    @Override
    @Transactional
    public void rapidBatchAddPigeon(List<Pigeon> pigeons, List<PigeonInfo> pigeonInfos, Long gid, String account, String fatherRingNumber, String motherRingNumber) {

        for (int i = 0, pigeonsSize = pigeons.size(); i < pigeonsSize; i++) {
            Pigeon pigeon = pigeons.get(i);

            //新增
            int insert = pigeonMapper.insert(pigeon);

            //检查
            if (!SqlHelper.retBool(insert)) {
                throw new SaveFailException("保存鸽子基础信息失败");
            }

            Long id = pigeon.getId();
            String ringNumber = pigeon.getRingNumber();

            //装填pid
            pigeonInfos.get(i).setPid(id);

            //记录日志
            oplogService.oplog(account, ringNumber, id, gid, LogType.INSERT);
            if (fatherRingNumber != null) {
                oplogService.oplog(account, ringNumber, id, gid, fatherRingNumber, LogType.RELATE);
            }
            if (motherRingNumber != null) {
                oplogService.oplog(account, ringNumber, id, gid, motherRingNumber, LogType.RELATE);
            }
        }

        pigeonInfos.forEach(pigeonInfo -> {
            int insert = pigeonInfoMapper.insert(pigeonInfo);

            //检查
            if (!SqlHelper.retBool(insert)) {
                throw new SaveFailException("保存鸽子额外信息失败");
            }
        });
    }

    @Override
    @Transactional
    public void savePigeonByFile(List<Map<String, PigeonWrapperVo>> pigeonWrappers, Long gid, String account) {
        //获取时间
        LocalDate now = LocalDate.now();

        //保存父代
        pigeonWrappers.forEach(pigeonMap -> {
            PigeonWrapperVo pigeonWrapperVo = pigeonMap.get("pigeon");
            //父
            PigeonWrapperVo father = pigeonMap.get("father");
            if (father != null) {
                //父fatherPigeon
                Pigeon fatherPigeon = father.getPigeon();

                //先根据足环检索是否存在
                QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
                wrapper.eq("ring_number", fatherPigeon.getRingNumber())
                        .eq("sex", "雄")
                        .eq("gid", gid);
                Pigeon selectPigeon = pigeonMapper.selectOne(wrapper);

                if (selectPigeon == null) {
                    //新增
                    fatherPigeon.setUpdateData(now);
                    fatherPigeon.setGid(gid);
                    int fatherPigeonInsert = pigeonMapper.insert(fatherPigeon);
                    if (!SqlHelper.retBool(fatherPigeonInsert)) {
                        throw new SaveFailException("父鸽基础信息保存失败");
                    }

                    //父fatherPigeonInfo
                    PigeonInfo fatherPigeonInfo = new PigeonInfo();
                    fatherPigeonInfo.setCreateTime(now);
                    fatherPigeonInfo.setPid(fatherPigeon.getId());
                    int fatherPigeonInfoInsert = pigeonInfoMapper.insert(fatherPigeonInfo);
                    if (!SqlHelper.retBool(fatherPigeonInfoInsert)) {
                        throw new SaveFailException("父鸽额外信息保存失败");
                    }

                    //给子代装填fid
                    pigeonWrapperVo.getPigeon().setFid(fatherPigeon.getId());

                    //记录日志
                    oplogService.oplog(account, fatherPigeon.getRingNumber(), fatherPigeon.getId(), gid, LogType.INSERT);
                } else {
                    //给子代装填fid
                    pigeonWrapperVo.getPigeon().setFid(selectPigeon.getId());
                }
            }

            //母
            PigeonWrapperVo mother = pigeonMap.get("mother");
            if (mother != null) {
                //母motherPigeon
                Pigeon motherPigeon = mother.getPigeon();

                //先根据足环检索是否存在
                QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
                wrapper.eq("ring_number", motherPigeon.getRingNumber())
                        .eq("sex", "雌")
                        .eq("gid", gid);
                Pigeon selectPigeon = pigeonMapper.selectOne(wrapper);

                if (selectPigeon == null) {
                    //新增
                    motherPigeon.setUpdateData(now);
                    motherPigeon.setGid(gid);
                    int fatherPigeonInsert = pigeonMapper.insert(motherPigeon);
                    if (!SqlHelper.retBool(fatherPigeonInsert)) {
                        throw new SaveFailException("母鸽基础信息保存失败");
                    }
                    //母motherPigeonInfo
                    PigeonInfo motherPigeonInfo = new PigeonInfo();
                    motherPigeonInfo.setCreateTime(now);
                    motherPigeonInfo.setPid(motherPigeon.getId());
                    int motherPigeonInfoInsert = pigeonInfoMapper.insert(motherPigeonInfo);
                    if (!SqlHelper.retBool(motherPigeonInfoInsert)) {
                        throw new SaveFailException("母鸽额外信息保存失败");
                    }
                    //给子代装填mid
                    pigeonWrapperVo.getPigeon().setMid(motherPigeon.getId());

                    //记录日志
                    oplogService.oplog(account, motherPigeon.getRingNumber(), motherPigeon.getId(), gid, LogType.INSERT);
                } else {
                    //给子代装填mid
                    pigeonWrapperVo.getPigeon().setMid(selectPigeon.getId());
                }
            }

            //保存子代信息
            //pigeon
            Pigeon pigeon = pigeonWrapperVo.getPigeon();
            pigeon.setUpdateData(now);
            pigeon.setGid(gid);
            int pigeonInsert = pigeonMapper.insert(pigeon);
            if (!SqlHelper.retBool(pigeonInsert)) {
                throw new SaveFailException("子代鸽基础信息保存失败");
            }
            //pigeonInfo
            PigeonInfo pigeonInfo = pigeonWrapperVo.getPigeonInfo();
            pigeonInfo.setCreateTime(now);
            pigeonInfo.setPid(pigeon.getId());
            int pigeonInfoInsert = pigeonInfoMapper.insert(pigeonInfo);
            if (!SqlHelper.retBool(pigeonInfoInsert)) {
                throw new SaveFailException("子代鸽额外信息保存失败");
            }

            //记录日志
            oplogService.oplog(account, pigeon.getRingNumber(), pigeon.getId(), gid, LogType.INSERT);
            if (father != null) {
                oplogService.oplog(account, pigeon.getRingNumber(),
                        pigeon.getId(), gid,
                        father.getPigeon().getRingNumber(),
                        LogType.RELATE
                );
            }
            if (mother != null) {
                oplogService.oplog(account, pigeon.getRingNumber(),
                        pigeon.getId(), gid,
                        mother.getPigeon().getRingNumber(),
                        LogType.RELATE
                );
            }
        });
    }
}