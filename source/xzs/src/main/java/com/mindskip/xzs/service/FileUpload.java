package com.mindskip.xzs.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Set;

public interface FileUpload {

    String uploadFile(MultipartFile file);

    String uploadFile(InputStream inputStream, long size, String extName);

}
