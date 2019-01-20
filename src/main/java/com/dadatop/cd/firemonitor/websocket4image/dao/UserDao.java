package com.dadatop.cd.firemonitor.websocket4image.dao;

import com.dadatop.cd.firemonitor.websocket4image.entity.User;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
