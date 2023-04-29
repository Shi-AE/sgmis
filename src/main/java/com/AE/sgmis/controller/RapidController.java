package com.AE.sgmis.controller;

import com.AE.sgmis.exceptions.SaveFailException;
import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.PigeonInfo;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.PigeonService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/rapid")
public class RapidController {

    @Autowired
    private PigeonService pigeonService;

    /**
     * 根据性别和传输值搜索
     */
    @GetMapping("{sex}/{value}")
    public Result searchPigeon(@PathVariable String sex, @PathVariable String value, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //条件 gid and sex and ring like value
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid)
                .eq(sex.equals("father"), "sex", "雄")
                .eq(sex.equals("mother"), "sex", "雌")
                .like("ring_number", value);

        //字段
        wrapper.select("id", "ring_number");

        List<Pigeon> list = pigeonService.list(wrapper);

        return new Result(list, SuccessCode.Success.code, "获取成功");
    }

    /**
     * 关联父代，快速入库子代
     * 记录日志
     */
    @PostMapping
    @SuppressWarnings("unchecked")
    public Result rapidAddPigeon(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        //解析请求体
        Long fid = map.get("fid") == null ? null : Long.valueOf((String) map.get("fid"));
        Long mid = map.get("mid") == null ? null : Long.valueOf((String) map.get("mid"));
        List<Map<String, Object>> pigeonMaps = (List<Map<String, Object>>) map.get("pigeons");

        //检查
        if (fid == null && mid == null) {
            throw new SaveFailException("信息不完整");
        }

        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //获取日期
        LocalDate now = LocalDate.now();

        //装配
        List<Pigeon> pigeons = new ArrayList<>();
        List<PigeonInfo> pigeonInfos = new ArrayList<>();
        pigeonMaps.forEach(pigeonMap -> {
            //pigeon
            Pigeon pigeon = new Pigeon();
            pigeon.setUpdateData(now);
            pigeon.setRingNumber((String) pigeonMap.get("ringNumber"));
            pigeon.setSex((String) pigeonMap.get("sex"));
            pigeon.setFid(fid);
            pigeon.setMid(mid);
            pigeon.setGid(gid);
            pigeons.add(pigeon);
            //pigeonInfo
            PigeonInfo pigeonInfo = new PigeonInfo();
            pigeonInfo.setCreateTime(now);
            pigeonInfos.add(pigeonInfo);
        });

        pigeonService.rapidBatchAddPigeon(pigeons, pigeonInfos);

        return new Result(SuccessCode.Success.code, "入库成功");
    }

}
