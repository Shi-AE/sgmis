package com.AE.sgmis.controller;

import com.AE.sgmis.exception.SaveFailException;
import com.AE.sgmis.pojo.Gsxx;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.GsxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/gsxx")
public class GsxxController {

    @Autowired
    private GsxxService gsxxService;

    @GetMapping
    public Result getGsxx(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //条件 gid = gid
        QueryWrapper<Gsxx> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);
        //执行
        Gsxx gsxx = gsxxService.getOne(wrapper);
        if (gsxx == null) {
            gsxx = new Gsxx();
        }
        //返回
        return new Result(gsxx, SuccessCode.Success.code, "查询成功");
    }

    @PostMapping
    public Result postGsxx(HttpServletRequest request, @RequestBody Gsxx gsxx) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //防止私自上传gid
        gsxx.setGid(gid);
        //条件 gid = gid
        QueryWrapper<Gsxx> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);
        //保存或者更新
        boolean success = gsxxService.saveOrUpdate(gsxx, wrapper);
        if (!success) {
            log.error("未知错误，传入 {} 导致保存失败", gsxx);
            throw new SaveFailException("保存失败");
        }
        //返回
        return new Result(SuccessCode.Success.code, "保存成功");
    }
}
