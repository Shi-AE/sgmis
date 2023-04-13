package com.AE.sgmis.util;

import com.AE.sgmis.exceptions.FileSaveException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

/**
 * 文件工具类
 */
@Slf4j
@Component
public class FileUtil {

    private final Tika tika = new Tika();

    /**
     * 将文件保存在本地
     */
    public String storeFile(MultipartFile file, Path path) {
        try {
            String uniqueFileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), path.resolve(uniqueFileName));
            return uniqueFileName;
        } catch (IOException e) {
            log.error("保存文件 {} 时发生错误", file);
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
            log.error("检查文件 {} 时发生错误", file);
            throw new FileSaveException("文件保存失败");
        }
    }

    /**
     * 检查文件类型是否匹配
     */
    public boolean checkFileType(MultipartFile file, List<String> typeList) {
        return typeList.contains(getFileType(file));
    }
}
