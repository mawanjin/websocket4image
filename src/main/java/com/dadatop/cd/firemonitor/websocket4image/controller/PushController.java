package com.dadatop.cd.firemonitor.websocket4image.controller;

import com.dadatop.cd.firemonitor.websocket4image.entity.Push;
import com.dadatop.cd.firemonitor.websocket4image.service.PushService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

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
    public String  pushList(Model model) {
        List<Push> pushes = pushService.findAllByStatus(0);

        model.addAttribute("pushes",pushes);

        return "/push/list";
    }




}
