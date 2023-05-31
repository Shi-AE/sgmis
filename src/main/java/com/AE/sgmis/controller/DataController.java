package com.AE.sgmis.controller;

import com.AE.sgmis.exceptions.MaliciousSqlInjectionException;
import com.AE.sgmis.pojo.*;
import com.AE.sgmis.result.*;
import com.AE.sgmis.service.*;
import com.AE.sgmis.util.RedisUtil;
import com.AE.sgmis.util.WhitelistUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("api/data")
public class DataController {

    @Autowired
    private PigeonService pigeonService;
    @Autowired
    private PigeonInfoService pigeonInfoService;
    @Autowired
    private OplogService oplogService;
    @Autowired
    private LoginMsgService loginMsgService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private WhitelistUtil whitelistUtil;
    @Autowired
    private UserService userService;
    @Value("${carousel.limit}")
    private Integer carouselLimit;
    @Value("${recent.create}")
    private Integer createRecent;
    @Value("${recent.delete}")
    private Integer deleteRecent;
    @Value("${page.maxLimit}")
    private Integer maxLimit;
    @Value("${recent.oplog}")
    private Integer oplogRecent;

    /**
     * 获取鸽子照片数据
     */
    @GetMapping("pigeon/picture")
    public Result getPigeonPicture(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //条件 gid = gid and picture_url is not null limit 10
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid)
                .isNotNull("picture_url")
                .last("limit " + carouselLimit);

        //字段 picture_url
        wrapper.select("picture_url");

        List<Pigeon> list = pigeonService.list(wrapper);
        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 获取创建时间变化数据
     * 根据设定的最近月数获取数据
     */
    @GetMapping("create")
    public Result getCreate(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //检索获取所有id
        QueryWrapper<Pigeon> pigeonWrapper = new QueryWrapper<>();
        pigeonWrapper.eq("gid", gid);
        pigeonWrapper.select("id");
        List<Pigeon> pigeonList = pigeonService.list(pigeonWrapper);

        //哈希计数map
        Map<String, Integer> countMap = new HashMap<>();
        //获取一个月前时间
        LocalDate recent = LocalDate.now().minusMonths(createRecent);
        pigeonList.forEach(pigeon -> {
            //根据id获取时间
            Long id = pigeon.getId();
            QueryWrapper<PigeonInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("pid", id)
                    .select("create_time");
            PigeonInfo pigeonInfo = pigeonInfoService.getOne(wrapper);
            //判断时间是否正确
            LocalDate createTime = pigeonInfo.getCreateTime();
            if (createTime.isAfter(recent)) {
                //如果存在
                countMap.computeIfPresent(createTime.toString(), (k, v) -> v + 1);
                //如果不存在
                countMap.computeIfAbsent(createTime.toString(), k -> 1);
            }
        });

        return new Result(countMap, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 获取删除时间变化数据
     * 根据设定的最近月数获取数据
     */
    @GetMapping("delete")
    public Result getDelete(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //获取最近时间
        LocalDateTime recent = LocalDateTime.now().minusMonths(deleteRecent);

        //条件语句
        //select DATE(time), count(0)
        //from t_oplog
        //where gid and content = 2 and time > recent
        //group by DATE(time)
        //order by time
        QueryWrapper<Oplog> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid)
                .eq("content", LogType.DELETE.getIndex())
                .gt("time", recent)
                .orderByAsc("time")
                .groupBy("DATE(time)")
                .select("DATE(time) time", "count(0) count");

        List<Map<String, Object>> countMap = oplogService.listMaps(wrapper);
        return new Result(countMap, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据gid pigeon统计总数
     */
    @GetMapping("pigeon/total")
    public Result getPigeonCount(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid)
                .select("count(0) total");

        Map<String, Object> count = pigeonService.getMap(wrapper);
        return new Result(count, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据gid oplog统计总数
     */
    @GetMapping("oplog/total")
    public Result getOplogCount(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        QueryWrapper<Oplog> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid)
                .select("count(0) total");

        Map<String, Object> count = oplogService.getMap(wrapper);
        return new Result(count, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据最近日期获取操作的数据变化
     */
    @GetMapping("oplog/line")
    public Result getOplogLine(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //获取最近时间
        LocalDateTime recent = LocalDateTime.now().minusMonths(oplogRecent);

        //select DATE(time), count(0), content
        //from t_oplog
        //where gid = 1640546214887645185
        //and time > '2023-04-01 20:22:49'
        //group by DATE(time), content
        //order by time;
        QueryWrapper<Oplog> wrapper = new QueryWrapper<>();
        wrapper.select("DATE(time) time", "content", "count(0) count")
                .eq("gid", gid)
                .gt("time", recent)
                .groupBy("DATE(time)", "content")
                .orderByAsc("time");
        List<Map<String, Object>> countMap = oplogService.listMaps(wrapper);

        return new Result(countMap, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 获取所有日期的操作数据
     */
    @GetMapping("oplog/content/total")
    public Result getContentTotal(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //分组统计
        QueryWrapper<Oplog> wrapper = new QueryWrapper<>();
        wrapper.select("count(0) count", "content")
                .eq("gid", gid)
                .groupBy("content");

        List<Map<String, Object>> countMap = oplogService.listMaps(wrapper);

        return new Result(countMap, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 获取最近几条的操作日志
     */
    @GetMapping("oplog/data/{limit}")
    public Result getOplogData(@PathVariable Integer limit, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        if (!(0 < limit && limit < maxLimit)) {
            throw new MaliciousSqlInjectionException(" limit异常", SeverityLevel.Major);
        }

        //条件 gid limit
        QueryWrapper<Oplog> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid)
                .orderByDesc("time")
                .last("LIMIT " + limit);

        List<Oplog> list = oplogService.list(wrapper);

        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据限制条数获取用户登录信息
     */
    @GetMapping("loginMsg/{limit}")
    public Result getLoginMsg(@PathVariable Integer limit, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        if (!(0 < limit && limit < maxLimit)) {
            throw new MaliciousSqlInjectionException(" limit异常", SeverityLevel.Major);
        }

        QueryWrapper<LoginMsg> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid)
                .orderByDesc("time")
                .last("LIMIT " + limit);

        List<LoginMsg> list = loginMsgService.list(wrapper);

        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 统计登录人员数据
     */
    @GetMapping("login/count")
    public Result getLoginCount(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //获取最近时间
        LocalDateTime recent = LocalDateTime.now().minusMonths(oplogRecent);

        //select account, count(0), DATE(time)
        //from t_login_msg
        //where gid = 1640546214887645185
        //and time > '2023-05-08 22:32:49'
        //group by DATE(time), account
        //order by time
        QueryWrapper<LoginMsg> wrapper = new QueryWrapper<>();
        wrapper.select("account", "count(0) count", "DATE(time) time")
                .eq("gid", gid)
                .gt("time", recent)
                .groupBy("DATE(time)", "account")
                .orderByAsc("time");

        List<Map<String, Object>> countMap = loginMsgService.listMaps(wrapper);

        return new Result(countMap, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 从缓存中获取在线人数
     */
    @GetMapping("online")
    public Result getOnline() {
        Object online = redisUtil.get(RedisNamespace.Online, "");
        return new Result(online, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 获取组员在线人数
     */
    @GetMapping("online/group")
    public Result getOnlineGroup(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //根据gid获取组内人员的id
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id")
                .eq("gid", gid);

        List<User> userList = userService.list(wrapper);

        AtomicLong count = new AtomicLong();

        userList.forEach(user -> {
            Long id = user.getId();
            long active = whitelistUtil.getActive(RedisNamespace.Whitelist, id + ":");
            count.addAndGet(active);
        });

        return new Result(count, SuccessCode.Success.code, "查询成功");
    }
}
