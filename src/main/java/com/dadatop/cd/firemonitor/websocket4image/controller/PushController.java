package com.dadatop.cd.firemonitor.websocket4image.controller;

import com.dadatop.cd.firemonitor.websocket4image.entity.Push;
import com.dadatop.cd.firemonitor.websocket4image.service.PushService;
import com.dadatop.cd.firemonitor.websocket4image.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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




}
