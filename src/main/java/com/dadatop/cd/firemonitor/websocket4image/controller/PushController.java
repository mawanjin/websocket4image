package com.dadatop.cd.firemonitor.websocket4image.controller;

import com.dadatop.cd.firemonitor.websocket4image.entity.Push;
import com.dadatop.cd.firemonitor.websocket4image.service.PushService;
import com.dadatop.cd.firemonitor.websocket4image.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

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
    public String  pushList(Model model,Page param) {
        Page<Push> page = pushService.findAllPage(param.getPageNo(),param.getPageSize());
        model.addAttribute("page",page);
        return "/push/list";
    }

    @RequestMapping("del")
    public String del(@RequestParam(name = "id")int id,Page page,Model model,RedirectAttributes attributes){
        pushService.del(id);
        attributes.addAttribute("pageNo",page.getPageNo());
        attributes.addAttribute("pageSize",page.getPageSize());
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
        return "redirect:list";
    }

}
