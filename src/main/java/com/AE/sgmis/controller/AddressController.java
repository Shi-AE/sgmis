package com.AE.sgmis.controller;


import com.AE.sgmis.pojo.Areas;
import com.AE.sgmis.pojo.Provincial;
import com.AE.sgmis.pojo.Urban;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.AreasService;
import com.AE.sgmis.service.ProvincialService;
import com.AE.sgmis.service.UrbanService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class AddressController {

    @Autowired
    private ProvincialService provincialService;
    @Autowired
    private UrbanService urbanService;
    @Autowired
    private AreasService areasService;

    @GetMapping("provincial")
    public Result getProvincial() {
        List<Provincial> list = provincialService.list();
        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    @GetMapping("urban/{id}")
    public Result getUrban(@PathVariable Long id) {
        QueryWrapper<Urban> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", id);
        List<Urban> list = urbanService.list(wrapper);
        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    @GetMapping("areas/{id}")
    public Result getAreas(@PathVariable Long id) {
        QueryWrapper<Areas> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", id);
        List<Areas> list = areasService.list(wrapper);
        return new Result(list, SuccessCode.Success.code, "查询成功");
    }
}