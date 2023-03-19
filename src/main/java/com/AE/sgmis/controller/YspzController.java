package com.AE.sgmis.controller;

import com.AE.sgmis.exception.DeleteFailException;
import com.AE.sgmis.exception.FieldsDuplicateException;
import com.AE.sgmis.exception.SaveFailException;
import com.AE.sgmis.pojo.Yspz;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.YspzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/yspz")
public class YspzController {

    @Autowired
    private YspzService yspzService;

    /**
     * 获取所有数据
     */
    @GetMapping
    public Result showList() {
        List<Yspz> allYs = yspzService.list();
        return new Result(allYs, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据id删除数据
     */
    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable("id") Long id) {
        boolean success = yspzService.removeById(id);
        if (!success) {
            throw new DeleteFailException("配置选项不存在，删除失败");
        }
        return new Result(SuccessCode.Success.code, "删除成功");
    }

    @Transactional
    @PostMapping("/delete")
    public Result batchDelete(@RequestBody Long[] ids) {
        for (Long id : ids) {
            boolean success = yspzService.removeById(id);
            if (!success) {
                throw new DeleteFailException("配置选项不存在，删除失败");
            }
        }
        return new Result(SuccessCode.Success.code, "删除成功");
    }

    /**
     * 新增选项配置
     */
    @PostMapping("/{name}")
    public Result add(@PathVariable String name) {
        Yspz yspz = new Yspz();
        try {
            yspz.setYs(name);
            yspz.setAuthor("系统");
            boolean success = yspzService.save(yspz);
            if (!success) {
                throw new SaveFailException("服务错误，添加失败");
            }
        } catch (DuplicateKeyException e) {
            throw new FieldsDuplicateException("\"" + name + "\"已经存在，无法添加重复的配置名称");
        }
        return new Result(yspz, SuccessCode.Success.code, "添加成功");
    }
}
