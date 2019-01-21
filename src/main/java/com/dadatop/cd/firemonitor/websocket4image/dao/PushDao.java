package com.dadatop.cd.firemonitor.websocket4image.dao;

import com.dadatop.cd.firemonitor.websocket4image.entity.Push;

import java.util.List;
import java.util.Map;

public interface PushDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Push record);

    int insertSelective(Push record);

    Push selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Push record);

    int updateByPrimaryKey(Push record);

    List<Push> findAllByStatus(int status);
    List<Push> findAll();
    List<Push> findAllPage(Map<String, Object> map);
    int findAllCount();
}
