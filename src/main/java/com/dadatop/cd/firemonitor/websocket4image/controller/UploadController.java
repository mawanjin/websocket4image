package com.dadatop.cd.firemonitor.websocket4image.controller;

import com.dadatop.cd.firemonitor.websocket4image.entity.Push;
import com.dadatop.cd.firemonitor.websocket4image.service.PushService;
import com.dadatop.cd.firemonitor.websocket4image.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.thymeleaf.util.DateUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/upload/")
public class UploadController {

    @Value("${upload.dir}")
    private String uploadDir;

    @Resource
    private PushService pushService;

    @RequestMapping("UploadAction")
    @ResponseBody
    public String upload(@RequestParam("fileList") MultipartFile multipartFile){

        //user_upload
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"/static/upload/images/";
        if(!new File(path).exists()){
            new File(path).mkdirs();
        }

        String oFileName = multipartFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString();
        File file = new File(path,newName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Push push = new Push();
        push.setOriginalName(oFileName);
        push.setFileName(newName);
        push.setAbPath(file.getAbsolutePath());
        String now = DateUtils.createNow().getTimeInMillis()+"";
        push.setUploadTime(now);
        push.setUpdateTime(now);

        pushService.addPush(push);

        return file.getAbsolutePath();
    }

}
