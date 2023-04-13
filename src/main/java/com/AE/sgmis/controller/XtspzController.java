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

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("api/xtspz")
public class XtspzController {

    @Autowired
    private FileUtil fileUtil;
    @Value("${file.logo.path}")
    private String basePath;
    @Autowired
    private XtspzService xtspzService;
    /**
     * 图片类型
     */
    private final List<String> type = Arrays.asList("image/jpeg", "image/png", "image/gif", "image/tiff");
    private Path path;

    /**
     * bean初始化
     */
    @PostConstruct
    public void init() {
        path = Paths.get(basePath);

        try {
            Files.createDirectory(path);
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            log.error("初始化bean发生错误，logo存储文件夹创建失败");
        }
    }

    /**
     * 上传logo图片
     */
    @PostMapping("logo")
    public Result uploadLogo(MultipartFile file, HttpServletRequest request) {
        //检查文件类型
        boolean checked = fileUtil.checkFileType(file, type);
        if (!checked) {
            throw new FileSaveException("文件类型错误，请上传图片");
        }
        //从请求域中获取用户信息
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //条件 gid = gid
        QueryWrapper<Xtspz> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);
        //判断原来是否存在图片
        Xtspz exist = xtspzService.getOne(wrapper);
        if (exist != null && exist.getLogoUrl() != null) {
            fileUtil.deleteFile(exist.getLogoUrl(), path);
        }

        //文保存到服务器
        if (Objects.isNull(file) || file.isEmpty()) {
            throw new FileSaveException("不能上传空文件");
        }
        String fileName = fileUtil.storeFile(file, path);

        //装填实体类
        Xtspz xtspz = new Xtspz();
        xtspz.setLogoUrl(fileName);
        xtspz.setGid(gid);

        boolean success = xtspzService.saveOrUpdate(xtspz, wrapper);
        if (!success) {
            //删除文件
            fileUtil.deleteFile(fileName, path);

            throw new FileSaveException("文件同步失败");
        }
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