package com.AE.sgmis.controller;

import com.AE.sgmis.exceptions.FileSaveException;
import com.AE.sgmis.exceptions.SaveFailException;
import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.PigeonInfo;
import com.AE.sgmis.pojo.PigeonWrapper;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.PigeonInfoService;
import com.AE.sgmis.service.PigeonService;
import com.AE.sgmis.util.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/pigeon")
public class PigeonController {

    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private PigeonService pigeonService;
    @Autowired
    private PigeonInfoService pigeonInfoService;
    @Value("${file.pigeon.path}")
    private String basePath;
    @Value("${file.pigeon.type}")
    private String[] typeArray;
    /**
     * 图片类型
     */
    private List<String> type;
    private Path path;

    @PostConstruct
    public void init() {
        //初始化资源
        path = Paths.get(basePath);
        type = List.of(typeArray);
        //初始化文件夹
        fileUtil.initDirectory(path);
    }

    /**
     * 上传鸽子照片
     */
    @PostMapping("picture")
    public Result uploadPigeonPicture(MultipartFile file) {
        //检查文件类型
        fileUtil.checkFileType(file, type);

        //文保存到服务器
        if (Objects.isNull(file) || file.isEmpty()) {
            throw new FileSaveException("不能上传空文件");
        }
        String fileName = fileUtil.storeFile(file, path);

        return new Result(fileName, SuccessCode.Success.code, "保存成功");
    }

    /**
     * 获取鸽子血统书信息
     */
    @GetMapping("{id}")
    public Result getPigeon(@PathVariable Long id, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //条件 id = id and gid = gid
        QueryWrapper<Pigeon> pigeonQueryWrapper = new QueryWrapper<>();
        pigeonQueryWrapper.eq("id", id).eq("gid", gid);

        //字段
        pigeonQueryWrapper.select(
                "id", "picture_url", "ring_number", "name", "bloodline",
                "sex", "fid", "mid", "yan", "ys", "lx", "is_grade"
        );

        Pigeon pigeon = pigeonService.getOne(pigeonQueryWrapper);

        //获取pid,以保证安全
        Long pid = pigeon.getId();

        //条件 pid = pid
        QueryWrapper<PigeonInfo> pigeonInfoQueryWrapper = new QueryWrapper<>();
        pigeonInfoQueryWrapper.eq("pid", pid);

        //字段
        pigeonInfoQueryWrapper.select("source", "sub_ring_number", "detail");

        PigeonInfo pigeonInfo = pigeonInfoService.getOne(pigeonInfoQueryWrapper);

        //包装
        PigeonWrapper pigeonWrapper = new PigeonWrapper();
        pigeonWrapper.setPigeon(pigeon);
        pigeonWrapper.setPigeonInfo(pigeonInfo);
        return new Result(pigeonWrapper, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 新增或更新鸽子
     */
    @PostMapping
    public Result addOrUpdatePigeon(@RequestBody PigeonWrapper pigeonWrapper, HttpServletRequest request) {
        //解析包装
        Pigeon pigeon = pigeonWrapper.getPigeon();
        PigeonInfo pigeonInfo = pigeonWrapper.getPigeonInfo();
        Long oid = pigeonWrapper.getOid();

        //检查必填参数
        if (pigeon.getRingNumber() == null) {
            throw new SaveFailException("足环不能为空");
        }
        if (pigeon.getSex() == null) {
            throw new SaveFailException("性别不能为空");
        }

        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //执行保存
        Long pid = pigeonService.savePigeon(pigeon, pigeonInfo, oid, gid);

        return new Result(pid, SuccessCode.Success.code, "上传成功");
    }
}
