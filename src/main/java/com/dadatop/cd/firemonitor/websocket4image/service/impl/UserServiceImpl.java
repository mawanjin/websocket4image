package com.dadatop.cd.firemonitor.websocket4image.service.impl;

import com.dadatop.cd.firemonitor.websocket4image.dao.UserDao;
import com.dadatop.cd.firemonitor.websocket4image.entity.User;
import com.dadatop.cd.firemonitor.websocket4image.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    public boolean addUser(User record){
        boolean result = false;
        try {
            userDao.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
