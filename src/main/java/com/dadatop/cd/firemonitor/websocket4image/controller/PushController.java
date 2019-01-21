package com.dadatop.cd.firemonitor.websocket4image.controller;

import com.dadatop.cd.firemonitor.websocket4image.entity.Push;
import com.dadatop.cd.firemonitor.websocket4image.service.PushService;
import com.dadatop.cd.firemonitor.websocket4image.util.FileUtil;
import com.dadatop.cd.firemonitor.websocket4image.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;

@Controller
@RequestMapping("/push")
public class PushController {

    @Resource
    private PushService pushService;

    @RequestMapping("")
    public String  pushIndex(Model model) {
        return "/push";
    }

    @RequestMapping("list")
    public String  pushList(Model model,Page param,String message) {
        Page<Push> page = pushService.findAllPage(param.getPageNo(),param.getPageSize());
        model.addAttribute("page",page);
        model.addAttribute("message",message);
        return "/push/list";
    }

    @RequestMapping("del")
    public String del(@RequestParam(name = "id")int id,Page page,Model model,RedirectAttributes attributes){
        pushService.del(id);
        attributes.addAttribute("pageNo",page.getPageNo());
        attributes.addAttribute("pageSize",page.getPageSize());
        attributes.addAttribute("message","操作成功");
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
        String path = "/Users/lala/Documents/workspace/aa/";
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



}
