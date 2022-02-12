package com.bei.service;

import com.bei.dao.UserDao;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    // 利用set动态实现值的注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }
}
