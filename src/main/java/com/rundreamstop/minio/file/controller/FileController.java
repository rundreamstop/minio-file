package com.rundreamstop.minio.file.controller;

import com.rundreamstop.minio.file.domain.R;
import com.rundreamstop.minio.file.domain.File;
import com.rundreamstop.minio.file.service.RunMinioFileService;
import com.rundreamstop.minio.file.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件请求处理
 *
 * @author zhangzihaopk@gmail.com
 */
@Slf4j
@RestController
public class FileController {

    @Autowired
    RunMinioFileService fileService;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public R<File> upload(MultipartFile file) {
        try {
            // 上传并返回访问地址
            String url = fileService.uploadFile(file);
            File file1 = new File();
            file1.setName(FileUtils.getName(url));
            file1.setUrl(url);
            return R.ok(file1);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }

    @DeleteMapping(value = "deleteFile")
    public R deleteFile(String fileName) {
        try {
            return fileService.deleteFile(fileName);
        } catch (Exception e) {
            log.error("删除文件失败", e);
            return R.fail(e.getMessage());
        }
    }
}