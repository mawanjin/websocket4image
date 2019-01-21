package com.dadatop.cd.firemonitor.websocket4image.service;

import com.dadatop.cd.firemonitor.websocket4image.entity.Config;
import com.dadatop.cd.firemonitor.websocket4image.entity.User;

import java.util.List;

public interface ConfigService {
    int deleteAll();
    List<Config> findAll();
    int insertSelective(Config config);
    int insert(Config config);
}
