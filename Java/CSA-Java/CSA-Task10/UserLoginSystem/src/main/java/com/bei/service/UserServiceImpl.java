package com.bei.service;

import com.bei.dao.UserMapper;
import com.bei.domain.User;

public class UserServiceImpl implements UserService {
    // service调用dao层，设置一个set，方便Spring管理
    private UserMapper userMapper;
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public User queryUserByMobile(String mobile) {
        return userMapper.queryUserByMobile(mobile);
    }
}
