package com.dadatop.cd.firemonitor.websocket4image.service;

import com.dadatop.cd.firemonitor.websocket4image.entity.User;

public interface UserService {
    public User getUserById(int userId);
    boolean addUser(User record);
}
