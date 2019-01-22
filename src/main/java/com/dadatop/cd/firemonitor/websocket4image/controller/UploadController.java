package com.dadatop.cd.firemonitor.websocket4image.controller;

import com.dadatop.cd.firemonitor.websocket4image.entity.Push;
import com.dadatop.cd.firemonitor.websocket4image.service.PushService;
import com.dadatop.cd.firemonitor.websocket4image.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
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
//        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"/static/upload/images/";
        String path = uploadDir;
        if(!new File(path).exists()){
            new File(path).mkdirs();
        }

        String oFileName = multipartFile.getOriginalFilename();
        String sufix = ".jpg";

        if(oFileName.contains(".")){
            sufix = oFileName.substring(oFileName.lastIndexOf("."));
        }

        String newName = UUID.randomUUID().toString()+sufix;
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
        Date now = new Date();
        push.setUploadTime(now);
        push.setUpdateTime(now);
        push.setPushTime(now);

        pushService.addPush(push);

        //保存到D:/images/下
        String targetPath = "D:\\images\\";
//        String targetPath = "/Users/lala/Documents/bbb/";

        try {
            File fout = new File(targetPath+System.currentTimeMillis()+"_"+newName);

            if(!fout.exists()){
                fout.createNewFile();
            }else {
                fout.delete();
            }
            FileUtil.CopyFile(file,fout);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath()+";and push success.";
    }

}
