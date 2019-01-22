package com.dadatop.cd.firemonitor.websocket4image.controller;

import com.alibaba.druid.util.StringUtils;
import com.dadatop.cd.firemonitor.websocket4image.entity.Config;
import com.dadatop.cd.firemonitor.websocket4image.entity.Push;
import com.dadatop.cd.firemonitor.websocket4image.service.ConfigService;
import com.dadatop.cd.firemonitor.websocket4image.service.PushService;
import com.dadatop.cd.firemonitor.websocket4image.util.FileUtil;
import com.dadatop.cd.firemonitor.websocket4image.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/push")
public class PushController {
    boolean dev = true;

    @Resource
    private PushService pushService;

    @Resource
    private ConfigService configService;

    @RequestMapping("")
    public String  pushIndex(Model model) {
        if(dev){
         return "/push";
        }
        return "push";
    }

    @RequestMapping("list")
    public String  pushList(Model model,Page param,String message) {
        Page<Push> page = pushService.findAllPage(param.getPageNo(),param.getPageSize());
        model.addAttribute("page",page);
        model.addAttribute("message",message);
        if(dev){
            return "/push/list";
        }
        return "push/list";
    }

    @RequestMapping("del")
    public String del(@RequestParam(name = "id")int id,Page page,Model model,RedirectAttributes attributes){
        String message = "操作成功";
        try {
            pushService.del(id);
            Push push = pushService.getPushById(id);
            if(push!=null){
                String pic = push.getAbPath();
                if(!StringUtils.isEmpty(pic)){
                    File p = new File(pic);
                    if(p.exists())p.delete();
                }
            }

        }catch (Exception e){
            message = "操作失败";
            e.printStackTrace();
        }
        attributes.addAttribute("pageNo",page.getPageNo());
        attributes.addAttribute("pageSize",page.getPageSize());
        attributes.addAttribute("message",message);
        return "redirect:list";
    }

    @RequestMapping("del_multi")
    public String del(@RequestParam(name = "ids")String ids,Page page,RedirectAttributes attributes){

        String[] idArray = ids.split(",");
        for (String i :idArray){
            try {
                pushService.del(Integer.parseInt(i));
            }catch (Exception e){}
        }

        attributes.addAttribute("pageNo",page.getPageNo());
        attributes.addAttribute("pageSize",page.getPageSize());
        attributes.addAttribute("message","操作成功");
        return "redirect:list";
    }

    @RequestMapping("dopush")
    public String doPush(@RequestParam(name = "id")int id,Page page,RedirectAttributes attributes){
        String message = "操作成功";
//        String path = "/Users/lala/Documents/workspace/aa/";
        String path = "D:\\images\\";
        Push p = pushService.getPushById(id);
        if(p!=null){
            path += p.getFileName();
        }

        try {
            File fout = new File(path);
            if(!fout.exists()){
                fout.createNewFile();
            }else {
                fout.delete();
            }

            File fin = new File(p.getAbPath());
            FileUtil.CopyFile(fin,fout);

            Date now = new Date();
            p.setPushTime(now);
            p.setUpdateTime(now);
            pushService.updateByPrimaryKeySelective(p);
        } catch (Exception e) {
            e.printStackTrace();
            message = "操作失败";
        }

        attributes.addAttribute("pageNo",page.getPageNo());
        attributes.addAttribute("pageSize",page.getPageSize());
        attributes.addAttribute("message",message);
        return "redirect:list";
    }

    @RequestMapping("config")
    public String  config(Model model) {
        if(dev){
            return "/push/config";
        }
        return "push/config";
    }

    @RequestMapping("doconfig")
    public String  doConfig(Model model,Config config) {
//        config.setId(1);
        configService.deleteAll();
        configService.insert(config);
        model.addAttribute("message","操作成功");
        if(dev){
            return "/push/config";
        }

        return "push/config";
    }

    @RequestMapping(value = "getconfig",produces = {"application/xml;charset=UTF-8"})
    @ResponseBody
    public String  getConfig(Model model) {
        try {
            List<Config> configs = configService.findAll();
            if(configs!=null && configs.size()>0){
                Config c = configs.get(0);
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><data><idle>"+c.getIdle()+"</idle><idletime>"+c.getIdletime()+"</idletime><load>"+c.getCload()+"</load><path>"+c.getPath()+"</path><size>"+c.getCsize()+"</size></data>";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "<data><idle>90</idle><idletime>5</idletime><load>1</load><path>images/</path><size>80</size></data>";
    }

}
