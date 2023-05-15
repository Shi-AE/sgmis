package com.AE.sgmis.controller;

import com.AE.sgmis.exceptions.FileSaveException;
import com.AE.sgmis.exceptions.SaveFailException;
import com.AE.sgmis.pojo.Xtspz;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.XtspzService;
import com.AE.sgmis.util.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("api/xtspz")
public class XtspzController {

    @Autowired
    private FileUtil fileUtil;
    @Value("${file.logo.path}")
    private String basePath;
    @Value("${file.logo.type}")
    private String[] typeArray;
    @Autowired
    private XtspzService xtspzService;
    /**
     * 图片类型
     */
    private Set<String> type;
    private Path path;

    /**
     * bean初始化
     */
    @PostConstruct
    public void init() {
        //初始化资源
        path = Paths.get(basePath);
        type = Set.of(typeArray);
        //初始化文件夹
        fileUtil.initDirectory(path);
    }

    /**
     * 上传logo图片
     */
    @PostMapping("logo")
    public Result uploadLogo(MultipartFile file) {

        if (Objects.isNull(file) || file.isEmpty()) {
            throw new FileSaveException("不能上传空文件");
        }

        //检查文件类型
        fileUtil.checkFileType(file, type);

        //文保存到服务器
        String fileName = fileUtil.storeFile(file, path);

        return new Result(fileName, SuccessCode.Success.code, "保存成功");
    }

    /**
     * 获取所有信息
     */
    @GetMapping
    public Result getInfo(HttpServletRequest request) {
        //从请求域中获取用户信息
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //条件 gid = gid
        QueryWrapper<Xtspz> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);
        //查询字段
        wrapper.select("logo_url", "name", "short_name", "phone", "mail", "url", "address");
        Xtspz xtspz = xtspzService.getOne(wrapper);
        if (xtspz == null) {
            xtspz = new Xtspz();
        }
        return new Result(xtspz, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 保存信息数据
     */
    @PostMapping
    public Result postInfo(@RequestBody Xtspz xtspz, HttpServletRequest request) {
        //从请求域中获取用户信息
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //条件 gid = gid
        QueryWrapper<Xtspz> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);
        //装填
        xtspz.setGid(gid);
        //执行
        boolean success = xtspzService.saveOrUpdate(xtspz, wrapper);
        if (!success) {
            throw new SaveFailException("信息保存失败");
        }
        return new Result(SuccessCode.Success.code, "保存成功");
    }
}
