package com.dadatop.cd.firemonitor.websocket4image.service;

import com.dadatop.cd.firemonitor.websocket4image.entity.Push;

import java.util.List;

public interface PushService {
    Push getPushById(int pushId);
    boolean addPush(Push record);
    List<Push> findAllByStatus(int status);
}
