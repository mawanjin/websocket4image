package com.dadatop.cd.firemonitor.websocket4image.service.impl;

import com.dadatop.cd.firemonitor.websocket4image.dao.ConfigDao;
import com.dadatop.cd.firemonitor.websocket4image.dao.PushDao;
import com.dadatop.cd.firemonitor.websocket4image.entity.Config;
import com.dadatop.cd.firemonitor.websocket4image.service.ConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private ConfigDao configDao;

    @Override
    public int deleteAll() {
        return configDao.deleteAll();
    }

    @Override
    public List<Config> findAll() {
        return configDao.findAll();
    }

    @Override
    public int insertSelective(Config config) {
        return configDao.insertSelective(config);
    }

    @Override
    public int insert(Config config) {
        return configDao.insert(config);
    }


}
