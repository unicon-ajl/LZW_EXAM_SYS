package com.mindskip.xzs.controller.student;


//import com.mindskip.xzs.base.BaseApiController;
//import com.mindskip.xzs.base.RestResponse;
//import com.mindskip.xzs.service.FileUpload;
//import com.mindskip.xzs.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.io.InputStream;
//
//
//@RequestMapping("/api/student/upload")
//@RestController("StudentUploadController")
//public class UploadController extends BaseApiController {
//
//    private final FileUpload fileUpload;
//    private final UserService userService;
//
//    @Autowired
//    public UploadController(FileUpload fileUpload, UserService userService) {
//        this.fileUpload = fileUpload;
//        this.userService = userService;
//    }
//
//
//    @RequestMapping("/image")
//    @ResponseBody
//    public RestResponse questionUploadAndReadExcel(HttpServletRequest request) {
//        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//        MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");
//        long attachSize = multipartFile.getSize();
//        String imgName = multipartFile.getOriginalFilename();
//        try (InputStream inputStream = multipartFile.getInputStream()) {
//            String filePath = fileUpload.uploadFile(inputStream, attachSize, imgName);
//            userService.changePicture(getCurrentUser(), filePath);
//            return RestResponse.ok(filePath);
//        } catch (IOException e) {
//            return RestResponse.fail(2, e.getMessage());
//        }
//    }
//
//
//}

import com.mindskip.xzs.base.BaseApiController;
import com.mindskip.xzs.base.RestResponse;
import com.mindskip.xzs.service.FileUpload;
import com.mindskip.xzs.service.UserService;
import com.mindskip.xzs.viewmodel.student.user.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/student/upload")
@RestController("StudentUploadController")
public class UploadController extends BaseApiController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    private final FileUpload fileUpload;
    private final UserService userService;

    @Autowired
    public UploadController(FileUpload fileUpload, UserService userService) {
        this.fileUpload = fileUpload;
        System.out.println(this.fileUpload);
        this.userService = userService;
    }

    @RequestMapping("/image")
    @ResponseBody
    public RestResponse questionUploadAndReadExcel(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");
        System.out.println(multipartFile.isEmpty());
        if (multipartFile == null || multipartFile.isEmpty()) {
            return RestResponse.fail(2, "上传的文件不能为空");
        }

        try {
            String filePath = fileUpload.uploadFile(multipartFile);
            System.out.println(filePath);
            userService.changePicture(getCurrentUser(), filePath);
            return RestResponse.ok(ConfigLoader.getUploadDir("vist") + filePath);
//            return RestResponse.ok(this.fileUpload.getUploadDirectory() + "/" + filePath);
        } catch (Exception e) {
            logger.error("文件上传失败", e);
            return RestResponse.fail(2, "文件上传失败");
        }
    }
}
