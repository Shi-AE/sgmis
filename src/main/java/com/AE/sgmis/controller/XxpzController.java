package com.AE.sgmis.controller;

import com.AE.sgmis.exception.DeleteFailException;
import com.AE.sgmis.exception.FieldsDuplicateException;
import com.AE.sgmis.exception.SaveFailException;
import com.AE.sgmis.pojo.Xxpz;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.XxpzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/xxpz/{tableName}")
public class XxpzController {

    @Autowired
    private XxpzService xxpzService;

    /**
     * 获取所有数据
     */
    @GetMapping
    public Result showList(@PathVariable String tableName) {
        List<Xxpz> xxpzs = xxpzService.dynamicList("t_" + tableName);
        return new Result(xxpzs, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据id删除数据
     */
    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable("id") Long id, @PathVariable String tableName) {
        boolean success = xxpzService.dynamicRemoveById(id, "t_" + tableName);
        if (!success) {
            throw new DeleteFailException("配置选项不存在，删除失败");
        }
        return new Result(SuccessCode.Success.code, "删除成功");
    }

    @Transactional
    @PostMapping("/delete")
    public Result batchDelete(@RequestBody Long[] ids, @PathVariable String tableName) {
        for (Long id : ids) {
            boolean success = xxpzService.dynamicRemoveById(id, "t_" + tableName);
            if (!success) {
                throw new DeleteFailException("配置选项不存在，删除失败");
            }
        }
        return new Result(SuccessCode.Success.code, "删除成功");
    }

    /**
     * 新增选项配置
     */
    // TODO 改路由
    @PostMapping("/{name}")
    public Result add(@PathVariable String name, @PathVariable String tableName) {
        Xxpz xxpz = new Xxpz();
        try {
            xxpz.setName(name);
            xxpz.setAuthor("系统");
            boolean success = xxpzService.dynamicSave(xxpz, "t_" + tableName);
            if (!success) {
                throw new SaveFailException("服务错误，添加失败");
            }
        } catch (DuplicateKeyException e) {
            throw new FieldsDuplicateException("\"" + name + "\"已经存在，无法添加重复的配置名称");
        }
        return new Result(xxpz, SuccessCode.Success.code, "添加成功");
    }
}
