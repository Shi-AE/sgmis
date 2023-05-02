package com.AE.sgmis.controller;

import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.PigeonService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/detail")
public class DetailController {

    @Value("${pigeon.generation.limit}")
    private Integer limit;
    @Autowired
    private PigeonService pigeonService;

    /**
     * 根据id查询子代列表
     */
    @GetMapping("offspring/{id}")
    public Result getOffspringList(@PathVariable Long id, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        List<ArrayList<Pigeon>> pigeonOffspringList = new ArrayList<>();

        //装填祖先
        {
            Pigeon pigeon = new Pigeon();
            pigeon.setId(id);
            ArrayList<Pigeon> pigeonList = new ArrayList<>();
            pigeonList.add(pigeon);
            pigeonOffspringList.add(pigeonList);
        }
        int generation = 0;

        //迭代
        while (generation < limit && pigeonOffspringList.get(generation).size() > 0) {
            ArrayList<Pigeon> pigeonList = pigeonOffspringList.get(generation);
            ArrayList<Pigeon> nextPigeonList = new ArrayList<>();

            pigeonList.forEach(pigeon -> {
                Long tid = pigeon.getId();
                //条件 gid = gid and (mid = id or fid = id)
                LambdaQueryWrapper<Pigeon> wrapper = new LambdaQueryWrapper<Pigeon>()
                        .eq(Pigeon::getGid, gid)
                        .and(w -> w.eq(Pigeon::getMid, tid).or().eq(Pigeon::getFid, tid));
                List<Pigeon> list = pigeonService.list(wrapper);
                nextPigeonList.addAll(list);
            });

            pigeonOffspringList.add(nextPigeonList);
            generation++;
        }

        return new Result(pigeonOffspringList, SuccessCode.Success.code, "查询成功");
    }


    /**
     * 根据id搜索同辈
     */
    @GetMapping("peer/{id}")
    public Result getPeerList(@PathVariable Long id, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //检索自己获取fid和mid
        //条件 id and gid
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid).eq("id", id);

        //字段
        wrapper.select("fid", "mid");

        Pigeon pigeon = pigeonService.getOne(wrapper);

        //没有找到父代
        if (pigeon == null) {
            return new Result(SuccessCode.Success.code, "查询成功");
        }


        Long fid = pigeon.getFid();
        Long mid = pigeon.getMid();

        Map<String, List<Pigeon>> pigeonMap = new HashMap<>();

        //同父异母
        if (fid != null) {
            //条件 fid = fid and id != id and gid = gid and mid != mid
            QueryWrapper<Pigeon> fatherHalfWrapper = new QueryWrapper<>();
            fatherHalfWrapper.eq("fid", fid)
                    .eq("gid", gid)
                    .ne(mid != null, "mid", mid)
                    .ne("id", id);
            List<Pigeon> list = pigeonService.list(fatherHalfWrapper);
            pigeonMap.put("fatherHalf", list);
        }

        //同母异父
        if (mid != null) {
            //条件 mid = mid and id != id and gid = gid and fid != fid
            QueryWrapper<Pigeon> motherHalfWrapper = new QueryWrapper<>();
            motherHalfWrapper.eq("mid", mid)
                    .eq("gid", gid)
                    .ne(fid != null, "fid", fid)
                    .ne("id", id);
            List<Pigeon> list = pigeonService.list(motherHalfWrapper);
            pigeonMap.put("motherHalf", list);
        }

        //同父同母
        if (fid != null && mid != null) {
            //条件 fid = fid and id != id and gid = gid and mid = mid
            QueryWrapper<Pigeon> fullWrapper = new QueryWrapper<>();
            fullWrapper.eq("fid", fid)
                    .eq("gid", gid)
                    .eq("mid", mid)
                    .ne("id", id);
            List<Pigeon> list = pigeonService.list(fullWrapper);
            pigeonMap.put("full", list);
        }

        return new Result(pigeonMap, SuccessCode.Success.code, "查询成功");
    }
}
