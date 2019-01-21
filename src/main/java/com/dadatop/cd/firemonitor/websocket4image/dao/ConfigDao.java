package com.dadatop.cd.firemonitor.websocket4image.dao;

import com.dadatop.cd.firemonitor.websocket4image.entity.Config;
import com.dadatop.cd.firemonitor.websocket4image.entity.Push;

import java.util.List;
import java.util.Map;

public interface ConfigDao {

    int deleteAll();
    List<Config> findAll();
    int insertSelective(Config config);
    int insert(Config config);
}
