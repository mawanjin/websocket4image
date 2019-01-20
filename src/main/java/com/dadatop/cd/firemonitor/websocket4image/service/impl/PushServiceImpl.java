package com.dadatop.cd.firemonitor.websocket4image.service.impl;

import com.dadatop.cd.firemonitor.websocket4image.dao.PushDao;
import com.dadatop.cd.firemonitor.websocket4image.entity.Push;
import com.dadatop.cd.firemonitor.websocket4image.service.PushService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("pushService")
public class PushServiceImpl implements PushService {
    @Resource
    private PushDao pushDao;

    public Push getPushById(int userId) {
        return pushDao.selectByPrimaryKey(userId);
    }

    public boolean addPush(Push push){
        boolean result = false;
        try {
            pushDao.insertSelective(push);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Push> findAllByStatus(int status) {
        return pushDao.findAllByStatus(status);
    }


}
