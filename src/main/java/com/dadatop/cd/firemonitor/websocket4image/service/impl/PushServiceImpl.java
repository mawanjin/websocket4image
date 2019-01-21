package com.dadatop.cd.firemonitor.websocket4image.service.impl;

import com.dadatop.cd.firemonitor.websocket4image.dao.PushDao;
import com.dadatop.cd.firemonitor.websocket4image.entity.Push;
import com.dadatop.cd.firemonitor.websocket4image.service.PushService;
import com.dadatop.cd.firemonitor.websocket4image.util.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Push> findAll() {
        return pushDao.findAll();
    }

    @Override
    public Page findAllPage(int currPage, int pageSize) {
        Page<Push> page = new Page();
        page.setPageNo(currPage);
        page.setPageSize(pageSize);
        page.setTotal(pushDao.findAllCount());
        Map<String, Object> data = new HashMap<>();
        data.put("currIndex", (currPage-1)*pageSize);
        data.put("pageSize", pageSize);
        page.setData(pushDao.findAllPage(data));
        return page;
    }

    @Override
    public void del(int id) {
        pushDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(Push p) {
        pushDao.updateByPrimaryKeySelective(p);
    }


}
