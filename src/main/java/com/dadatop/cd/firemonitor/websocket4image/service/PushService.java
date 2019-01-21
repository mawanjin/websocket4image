package com.dadatop.cd.firemonitor.websocket4image.service;

import com.dadatop.cd.firemonitor.websocket4image.entity.Push;
import com.dadatop.cd.firemonitor.websocket4image.util.Page;

import java.util.List;
import java.util.Map;

public interface PushService {
    Push getPushById(int pushId);
    boolean addPush(Push record);
    List<Push> findAllByStatus(int status);
    List<Push> findAll();
    Page findAllPage(int currPage, int pageSize);
}
