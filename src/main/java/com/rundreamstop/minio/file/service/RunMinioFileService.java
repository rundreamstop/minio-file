package com.rundreamstop.minio.file.service;

import com.rundreamstop.minio.file.config.MinioConfig;
import com.rundreamstop.minio.file.domain.R;
import com.rundreamstop.minio.file.utils.FileUtils;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Minio 文件存储
 *
 * @author zhangzihaopk@gmail.com
 */
@Service
public class RunMinioFileService {
    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioClient client;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws Exception {
        String fileName = FileUtils.extractFilename(file);
        PutObjectArgs args = PutObjectArgs.builder().
                bucket(minioConfig.getBucketName()).
                object(fileName).
                stream(file.getInputStream(), file.getSize(), -1).
                contentType(file.getContentType()).
                build();
        client.putObject(args);
        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public R deleteFile(String fileName) throws Exception {

        RemoveObjectArgs args = RemoveObjectArgs.builder().
                bucket(minioConfig.getBucketName()).
                object(fileName).
                build();
        client.removeObject(args);
        return R.ok();
    }
}
