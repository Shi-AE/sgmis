package com.AE.sgmis.controller;

import com.AE.sgmis.exceptions.DeleteFailException;
import com.AE.sgmis.exceptions.MaliciousSqlInjectionException;
import com.AE.sgmis.exceptions.SaveFailException;
import com.AE.sgmis.pojo.Xxpz;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.XxpzService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("api/xxpz")
public class XxpzController {

    @Autowired
    private XxpzService xxpzService;
    @Value("${xxpz.systemGid}")
    private Integer systemGid;
    @Value("${xxpz.tableNames}")
    private String[] names;
    private Set<String> tableNames;

    @PostConstruct
    public void init() {
        tableNames = Set.of(names);
        log.info("选项配置类型防注入set初始化完成");
    }

    /**
     * 获取所有类型的所有数据
     */
    @GetMapping
    public Result getAll(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //添加条件 gid or gid = systemGid
        QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid).or().eq("gid", systemGid);

        //字段
        wrapper.select("name", "type");

        //查询
        List<Xxpz> list = xxpzService.list(wrapper);
        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据类型获取所有数据
     */
    @GetMapping("{type}")
    public Result getAllByType(@PathVariable String type, HttpServletRequest request) {
        //判断是否异常type注入
        if (!tableNames.contains(type)) {
            throw new MaliciousSqlInjectionException("配置类型异常注入");
        }

        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //添加条件 type and gid or gid
        QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
        wrapper.eq("type", type).and(qw -> qw.eq("gid", systemGid).or().eq("gid", gid));

        //查询
        List<Xxpz> list = xxpzService.list(wrapper);
        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据id删除数据
     */
    @DeleteMapping("{id}")
    public Result deleteOne(@PathVariable("id") Long id, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //添加条件 id = id and gid = gid
        QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid).eq("id", id);

        //执行
        boolean success = xxpzService.remove(wrapper);
        if (!success) {
            throw new DeleteFailException("配置选项不存在或无法删除");
        }
        return new Result(SuccessCode.Success.code, "删除成功");
    }

    /**
     * 批量删除选项配置
     */
    @Transactional
    @PostMapping("delete")
    public Result batchDelete(@RequestBody List<Long> ids, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //条件 (id in ids) and gid = gid
        QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
        wrapper.in("id", ids).eq("gid", gid);

        boolean success = xxpzService.remove(wrapper);
        if (!success) {
            throw new DeleteFailException("配置选项不存在或无法删除");
        }

        return new Result(SuccessCode.Success.code, "删除成功");
    }

    /**
     * 新增选项配置
     */
    // TODO 数据库控制输入数据
    @PostMapping("{type}")
    public Result add(@RequestBody Xxpz xxpz, @PathVariable String type, HttpServletRequest request) {
        //判断是否异常type注入
        if (!tableNames.contains(type)) {
            throw new MaliciousSqlInjectionException("配置类型异常注入");
        }

        //从请求域中获取用户信息
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        xxpz.setAuthor((String) info.get("account"));
        xxpz.setGid((Long) info.get("gid"));
        xxpz.setType(type);

        boolean success = xxpzService.save(xxpz);
        if (!success) {
            log.error("新增配置传入 {} 时发生服务错误", xxpz);
            throw new SaveFailException("服务错误，添加失败");
        }

        return new Result(xxpz, SuccessCode.Success.code, "添加成功");
    }

    /**
     * 根据id更新数据
     */
    @PutMapping
    public Result update(@RequestBody Xxpz xxpz, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //安全检查
        if (xxpz.getGid() == null || !xxpz.getGid().equals(gid)) {
            throw new SaveFailException("用户信息不匹配，请重试");
        }

        //条件 id = id and gid = gid
        QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
        wrapper.eq("id", xxpz.getId()).eq("gid", gid);

        //执行
        boolean success = xxpzService.update(xxpz, wrapper);
        if (!success) {
            throw new SaveFailException("数据更新失败，请重试");
        }
        return new Result(SuccessCode.Success.code, "更新成功");
    }
}
