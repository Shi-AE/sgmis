package com.AE.sgmis.controller;

import com.AE.sgmis.exceptions.MaliciousSqlInjectionException;
import com.AE.sgmis.exceptions.NotFoundException;
import com.AE.sgmis.pojo.Oplog;
import com.AE.sgmis.pojo.vo.PagingConditionVo;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SeverityLevel;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.OplogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/oplog")
public class OplogController {

    @Autowired
    private OplogService oplogService;
    @Value("${page.maxLimit}")
    private Long maxLimit;

    /**
     * 根据pid获取鸽子日志
     * 提供limit接口控制查询数量
     */
    @GetMapping("{pid}/{limit}")
    public Result getLogById(@PathVariable Long pid, @PathVariable Integer limit, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        if (!(0 < limit && limit < maxLimit)) {
            throw new MaliciousSqlInjectionException(" limit异常", SeverityLevel.Major);
        }

        //条件
        QueryWrapper<Oplog> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", pid)
                .eq("gid", gid)
                .orderByDesc("time")
                .last("LIMIT " + limit);

        List<Oplog> list = oplogService.list(wrapper);

        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据分页条件获取页面信息
     */
    @PostMapping
    public Result getConditionPage(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") PagingConditionVo condition, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //解析请求体
        Integer content = condition.getContent();
        String tip = condition.getTip();
        String ringNumber = condition.getRingNumber();
        List<LocalDateTime> timeRange = condition.getTimeRange();
        String author = condition.getAuthor();
        Integer current = condition.getCurrent();
        Integer pageSize = condition.getPageSize();

        if (current == null || pageSize == null) {
            log.error("PagingCondition {} 缺少必要条件", condition);
            throw new NotFoundException("服务错误");
        }

        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (timeRange != null && timeRange.size() == 2) {
            startTime = timeRange.get(0);
            endTime = timeRange.get(1);
        }

        //条件
        QueryWrapper<Oplog> wrapper = new QueryWrapper<>();
        wrapper.eq(content != null, "content", content)
                .like(tip != null, "tip", tip)
                .like(ringNumber != null, "ring_number", ringNumber)
                .like(author != null, "author", author)
                .between(startTime != null && endTime != null, "time", startTime, endTime)
                .orderByDesc("time")
                .eq("gid", gid);

        //分页
        Page<Oplog> page = new Page<>();
        page.setCurrent(current)
                .setSize(pageSize)
                .setMaxLimit(maxLimit);


        page = oplogService.page(page, wrapper);

        return new Result(page, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 批量自定义日志
     */
    @PutMapping
    public Result batchAddLog(@RequestBody List<Oplog> oplogs, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        String account = (String) info.get("account");

        oplogService.batchAddLog(oplogs, gid, account);
        return new Result(SuccessCode.Success.code, "记录日志成功");
    }
}
