package com.mindskip.xzs.viewmodel.student.user;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final String CONFIG_FILE = "application.properties";

//  上传图片时，由于是本地存储，所以路径应为具体的文件路径，例如：D:/design-Item/xzs_online_exam_sys/xzs-mysql/source/uploads/
    static String path = "file.upload-dir";
    public static String getUploadDir(String flag) {
        if(flag != null){
//          本地访问图片时，要通过静态服务器访问图片，路径应为：http://localhost:8888/
            path = "file.visit-dir";
        }

        Properties props = new Properties();
        try (InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (is == null) {
                throw new IllegalStateException("application.properties not found in the classpath.");
            }
            props.load(is);
            return props.getProperty(path);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public static void main(String args) {
        String uploadDir = getUploadDir(args);
        System.out.println("Upload Directory: " + uploadDir);
    }
}