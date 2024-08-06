package com.mindskip.xzs.service.impl;
//
//import com.mindskip.xzs.configuration.property.QnConfig;
//import com.mindskip.xzs.configuration.property.SystemConfig;
//import com.mindskip.xzs.service.FileUpload;
//import com.google.gson.Gson;
//import com.qiniu.common.QiniuException;
//import com.qiniu.http.Response;
//import com.qiniu.storage.Configuration;
//import com.qiniu.storage.Region;
//import com.qiniu.storage.UploadManager;
//import com.qiniu.storage.model.DefaultPutRet;
//import com.qiniu.util.Auth;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.InputStream;
//
//@Service
//public class FileUploadImpl implements FileUpload {
//    private final Logger logger = LoggerFactory.getLogger(FileUpload.class);
//    private final SystemConfig systemConfig;
//
//
//    @Autowired
//    public FileUploadImpl(SystemConfig systemConfig) {
//        this.systemConfig = systemConfig;
//    }
//
//    @Override
//    public String uploadFile(InputStream inputStream, long size, String extName) {
//        QnConfig qnConfig = systemConfig.getQn();
//        Configuration cfg = new Configuration(Region.region2());
//        UploadManager uploadManager = new UploadManager(cfg);
//        Auth auth = Auth.create(qnConfig.getAccessKey(), qnConfig.getSecretKey());
//        String upToken = auth.uploadToken(qnConfig.getBucket());
//        try {
//            Response response = uploadManager.put(inputStream, null, upToken, null, null);
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            return qnConfig.getUrl() + "/" + putRet.key;
//        } catch (QiniuException ex) {
//            logger.error(ex.getMessage(), ex);
//        }
//        return null;
//    }
//}



import com.mindskip.xzs.service.FileUpload;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileUploadImpl implements FileUpload {
    private final Logger logger = LoggerFactory.getLogger(FileUpload.class);
    private final String uploadDirectory;

    @Autowired
    public FileUploadImpl(@Value("${file.upload-dir}") String uploadDirectory) {
        this.uploadDirectory = uploadDirectory;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("上传的文件不能为空");
            }

            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
            String uniqueFilename = UUID.randomUUID().toString() + "." + extension;

            // 使用 UTF-8 编码确保路径中只包含支持的字符
            Path uploadPath = Paths.get(new String(this.uploadDirectory.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8), uniqueFilename);
            System.out.println(uploadPath);
            Files.createDirectories(uploadPath.getParent()); // 创建父目录
            Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFilename;
        } catch (IOException e) {
            // 日志记录
            logger.error(() -> "文件上传失败", e);
            return null;
        }
    }

    @Override
    public String uploadFile(InputStream inputStream, long size, String extName) {
        return null;
    }

    // 新增一个方法用于获取文件上传目录
    public String getUploadDirectory() {
        return this.uploadDirectory;
    }
}