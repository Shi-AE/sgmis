package com.AE.sgmis.controller;

import com.AE.sgmis.exceptions.MaliciousSqlInjectionException;
import com.AE.sgmis.exceptions.SaveFailException;
import com.AE.sgmis.pojo.Gpcx;
import com.AE.sgmis.pojo.PigeonGpcx;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.GpcxService;
import com.AE.sgmis.service.PigeonGpcxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/gpcx")
public class GpcxController {

    @Autowired
    private GpcxService gpcxService;
    @Autowired
    private PigeonGpcxService pigeonGpcxService;

    /**
     * 获取鸽棚巢箱信息
     */
    @GetMapping
    public Result getGpcx(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //查询鸽棚巢箱
        //条件 gid = gid
        QueryWrapper<Gpcx> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);
        //查询
        wrapper.select("id", "name", "cx_number", "gid");
        //执行
        List<Gpcx> list = gpcxService.list(wrapper);
        //查询鸽子数量
        list.forEach(item -> {
            Long id = item.getId();
            //条件 gpcx_id = id
            QueryWrapper<PigeonGpcx> countWrapper = new QueryWrapper<>();
            countWrapper.eq("gpcx_id", id);
            //执行计数
            int count = (int) pigeonGpcxService.count(countWrapper);
            item.setPigeonPopulation(count);
        });
        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 获取鸽棚巢箱名称和id基本信息
     * 用于为鸽棚巢箱添加鸽子
     */
    @GetMapping("base")
    public Result getBaseGpcx(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //查询鸽棚巢箱
        //条件 gid = gid
        QueryWrapper<Gpcx> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);
        //查询
        wrapper.select("id", "name");
        //执行
        List<Gpcx> list = gpcxService.list(wrapper);
        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 添加鸽棚巢箱
     */
    @PostMapping
    public Result addGpcx(@RequestBody Gpcx gpcx, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //装填
        gpcx.setGid(gid);

        boolean success = gpcxService.save(gpcx);
        if (!success) {
            throw new SaveFailException("服务错误，保存失败");
        }

        gpcx.setPigeonPopulation(0);

        return new Result(gpcx, SuccessCode.Success.code, "保存成功");
    }

    /**
     * 将鸽子添加到鸽棚巢箱
     * 记录日志
     */
    @PostMapping("{gpcxId}")
    public Result addPigeonToGpcx(@RequestBody List<Long> ids, @PathVariable Long gpcxId, HttpServletRequest request) {
        //检查权限
        String name = check(gpcxId, request);
        //获取账号名
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        String account = (String) info.get("account");

        //更新或添加
        int update = pigeonGpcxService.addPigeonToGpcx(ids, gpcxId, name, account, gid);

        return new Result(SuccessCode.Success.code, "全部保存成功，发生" + update + "个更新，" + (ids.size() - update) + "个新增");
    }

    /**
     * 修改鸽棚巢箱信息
     */
    @PutMapping
    public Result updateGpcx(@RequestBody Gpcx gpcx, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //检查gid安全
        if (!gpcx.getGid().equals(gid)) {
            throw new SaveFailException("用户信息不匹配，请重试");
        }
        //id = id and gid = gid
        QueryWrapper<Gpcx> wrapper = new QueryWrapper<>();
        wrapper.eq("id", gpcx.getId()).eq("gid", gid);
        //执行
        boolean success = gpcxService.update(gpcx, wrapper);
        if (!success) {
            log.error("重置密码传入 {} 发生服务器错误", gpcx);
            throw new SaveFailException("服务器错误，保存失败，请重试");
        }
        return new Result(SuccessCode.Success.code, "更新成功");
    }

    /**
     * 获取鸽棚巢箱中鸽子的数量
     */
    @GetMapping("pigeon/{gpcxId}")
    public Result getPigeonNumber(@PathVariable Long gpcxId, HttpServletRequest request) {
        //检查权限
        check(gpcxId, request);
        //执行查询
        //条件 gpcxId = gpcxId
        QueryWrapper<PigeonGpcx> wrapper = new QueryWrapper<>();
        wrapper.eq("gpcx_id", gpcxId);
        //执行计数
        long count = pigeonGpcxService.count(wrapper);
        return new Result(count, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 删除鸽棚巢箱
     */
    @DeleteMapping("{gpcxId}")
    public Result removeGpcx(@PathVariable Long gpcxId, HttpServletRequest request) {
        //检查权限
        check(gpcxId, request);
        //执行删除
        Integer count = gpcxService.gpcxRemoveById(gpcxId);
        return new Result(count, SuccessCode.Success.code, "删除成功");
    }

    /**
     * 检查权限
     */
    private String check(Long gpcxId, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //条件 id = gpcxId
        QueryWrapper<Gpcx> wrapper = new QueryWrapper<>();
        wrapper.eq("id", gpcxId);
        //字段 gid
        wrapper.select("gid", "name");
        //执行
        Gpcx gpcx = gpcxService.getOne(wrapper);
        //检查
        if (!gid.equals(gpcx.getGid())) {
            throw new MaliciousSqlInjectionException("非法查询");
        }
        return gpcx.getName();
    }
}
