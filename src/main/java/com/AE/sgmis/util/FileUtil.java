package com.AE.sgmis.util;

import com.AE.sgmis.exceptions.FileSaveException;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;

/**
 * 文件工具类
 */
@Slf4j
@Component
public class FileUtil {

    private final Tika tika = new Tika();

    /**
     * 初始化文件夹
     */
    public void initDirectory(Path path) {
        try {
            Files.createDirectory(path);
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            log.error("初始化bean发生错误，{} 存储文件夹创建失败", path.getFileName(), e);
        }
    }

    /**
     * 将文件保存在本地
     */
    public String storeFile(MultipartFile file, Path path) {
        try {
            String uniqueFileName = IdWorker.get32UUID() + "-" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), path.resolve(uniqueFileName));
            return uniqueFileName;
        } catch (IOException e) {
            log.error("保存文件 {} 时发生错误", file, e);
            throw new FileSaveException("文件保存失败");
        }
    }

    /**
     * 根据文件目录获取一级子文件
     */
    public Stream<Path> getFileListByDirectory(Path dirPath) {
        try {
            return Files.list(dirPath);
        } catch (IOException e) {
            log.error("查询文件目录 {} 时发生错误", dirPath, e);
            throw new FileSaveException("文件保存失败");
        }
    }

    /**
     * 删除文件
     */
    public boolean deleteFile(String fileName, Path path) {
        try {
            Files.deleteIfExists(path.resolve(fileName));
            return true;
        } catch (IOException e) {
            log.error("文件删除失败，当文件名为 {}，path为 {}", fileName, path);
            return false;
        }
    }

    /**
     * 获得文件类型
     */
    public String getFileType(MultipartFile file) {
        try {
            return tika.detect(file.getInputStream());
        } catch (IOException e) {
            log.error("检查文件 {} 时文件流发生错误", file, e);
            throw new FileSaveException("文件保存失败");
        }
    }

    /**
     * 通过文件类型列表
     * 检查文件类型是否匹配
     */
    @SuppressWarnings("UnusedReturnValue")
    public String checkFileType(MultipartFile file, Set<String> typeList) {
        String fileType = getFileType(file);
        boolean checked = typeList.contains(fileType);
        if (!checked) {
            throw new FileSaveException("文件类型错误，请上传正确类型");
        }
        return fileType;
    }

    /**
     * 通过多字符串
     * 检查文件类型是否匹配
     */
    public String checkFileType(MultipartFile file, String... types) {
//        List<String> typeList = List.of(types);
        Set<String> typeList = Set.of(types);
        String fileType = getFileType(file);
        boolean checked = typeList.contains(fileType);
        if (!checked) {
            throw new FileSaveException("文件类型错误，请上传正确类型");
        }
        return fileType;
    }
}
