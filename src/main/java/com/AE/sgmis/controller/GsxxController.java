package com.AE.sgmis.controller;

import com.AE.sgmis.exception.SaveFailException;
import com.AE.sgmis.pojo.Gsxx;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.GsxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/gsxx")
//todo 内部系统取消uid的区分
public class GsxxController {

    @Autowired
    private GsxxService gsxxService;

    @GetMapping
    public Result getGsxx(HttpServletRequest request) {
        //获取id
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long id = (Long) info.get("id");
        //匹配规则
        QueryWrapper<Gsxx> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", id);

        Gsxx gsxx = gsxxService.getOne(wrapper);
        //防止前端空值
        if (gsxx == null) {
            gsxx = new Gsxx();
        }

        return new Result(gsxx, SuccessCode.Success.code, "查询成功");
    }

    @PostMapping
    public Result postGsxx(HttpServletRequest request, @RequestBody Gsxx gsxx) {
        //获取id
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long id = (Long) info.get("id");
        //匹配规则
        QueryWrapper<Gsxx> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", id);
        //如果是save则要用到id
        gsxx.setUid(id);
        //更新或者新建
        boolean success = gsxxService.saveOrUpdate(gsxx, wrapper);
        if (!success) {
            throw new SaveFailException("提交失败");
        }
        return new Result(SuccessCode.Success.code, "保存成功");
    }
}
