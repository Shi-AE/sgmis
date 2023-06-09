package com.AE.sgmis.controller;

import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.PigeonInfo;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.PigeonInfoService;
import com.AE.sgmis.service.PigeonService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/statistic")
public class StatisticController {

    @Autowired
    private PigeonService pigeonService;
    @Autowired
    private PigeonInfoService pigeonInfoService;

    /**
     * 根据gid获取用户鸽子数据中的雌雄数量
     */
    @GetMapping("sex")
    public Result getSexData(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //select count(sex), sex
        //from t_pigeon
        //where sex is not null
        //and gid = 1640546214887645185
        //group by sex;
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.select("sex", "COUNT(sex) count")
                .eq("gid", gid)
                .isNotNull("sex")
                .groupBy("sex");

        List<Map<String, Object>> maps = pigeonService.listMaps(wrapper);

        return new Result(maps, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据gid获取眼色统计数据
     * 并排序返回
     */
    @GetMapping("yan")
    public Result getYanData(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //select count(yan), yan
        //from t_pigeon
        //where yan is not null
        //and gid = 1640546214887645185
        //group by yan
        //order by count(yan);
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.select("yan", "COUNT(yan) count")
                .eq("gid", gid)
                .isNotNull("yan")
                .groupBy("yan")
                .orderByDesc("COUNT(yan)");

        List<Map<String, Object>> maps = pigeonService.listMaps(wrapper);

        return new Result(maps, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据gid获取羽色统计数据
     * 并排序返回
     */
    @GetMapping("ys")
    public Result getYsData(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //select count(ys), ys
        //from t_pigeon
        //where ys is not null
        //and gid = 1640546214887645185
        //group by ys
        //order by count(ys);
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.select("ys", "COUNT(ys) count")
                .eq("gid", gid)
                .isNotNull("ys")
                .groupBy("ys")
                .orderByDesc("COUNT(ys)");

        List<Map<String, Object>> maps = pigeonService.listMaps(wrapper);

        return new Result(maps, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据gid获取用户鸽子数据中的赛绩鸽数量
     */
    @GetMapping("grade")
    public Result getGradeData(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //select count(is_grade), is_grade
        //from is_grade
        //where is_grade is not null
        //and gid = 1640546214887645185
        //group by is_grade;
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.select("is_grade isGrade", "COUNT(is_grade) count")
                .eq("gid", gid)
                .isNotNull("is_grade")
                .groupBy("is_grade");

        List<Map<String, Object>> maps = pigeonService.listMaps(wrapper);

        return new Result(maps, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据gid获取类型统计数据
     */
    @GetMapping("lx")
    public Result getLxData(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //select count(lx), lx
        //from t_pigeon
        //where lx is not null
        //and gid = 1640546214887645185
        //group by lx;
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.select("lx", "COUNT(lx) count")
                .eq("gid", gid)
                .isNotNull("lx")
                .groupBy("lx");

        List<Map<String, Object>> maps = pigeonService.listMaps(wrapper);

        return new Result(maps, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据gid获取状态统计数据
     */
    @GetMapping("state")
    public Result getStateData(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //select count(state), state
        //from t_pigeon
        //where state is not null
        //and gid = 1640546214887645185
        //group by state;
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.select("state", "COUNT(state) count")
                .eq("gid", gid)
                .isNotNull("state")
                .groupBy("state");

        List<Map<String, Object>> maps = pigeonService.listMaps(wrapper);

        return new Result(maps, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据gid获取级别统计数据
     */
    @GetMapping("jb")
    public Result getJbData(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //select count(jb), jb
        //from t_pigeon
        //where jb is not null
        //and gid = 1640546214887645185
        //group by jb;
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.select("jb", "COUNT(jb) count")
                .eq("gid", gid)
                .isNotNull("jb")
                .groupBy("jb");

        List<Map<String, Object>> maps = pigeonService.listMaps(wrapper);

        return new Result(maps, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据gid获取作育者的统计数据
     */
    @GetMapping("source")
    public Result getSourceData(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //根据gid获取所有的pid
        QueryWrapper<Pigeon> pigeonQueryWrapper = new QueryWrapper<>();
        pigeonQueryWrapper.select("id")
                .eq("gid", gid);

        List<Pigeon> pigeonList = pigeonService.list(pigeonQueryWrapper);

        Map<String, Integer> countMap = new HashMap<>();
        //根据pid获取source
        pigeonList.forEach(pigeon -> {
            Long id = pigeon.getId();

            QueryWrapper<PigeonInfo> infoQueryWrapper = new QueryWrapper<>();
            infoQueryWrapper.select("source")
                    .eq("pid", id);

            PigeonInfo pigeonInfo = pigeonInfoService.getOne(infoQueryWrapper);
            if (pigeonInfo == null) {
                return;
            }
            //map中没有，赋值为一
            countMap.computeIfAbsent(pigeonInfo.getSource(), k -> 1);
            //map中存在则计数
            countMap.computeIfPresent(pigeonInfo.getSource(), (k, v) -> v + 1);
        });

        return new Result(countMap, SuccessCode.Success.code, "获取成功");
    }
}
