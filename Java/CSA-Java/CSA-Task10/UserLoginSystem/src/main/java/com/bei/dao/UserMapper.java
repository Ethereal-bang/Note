package com.bei.dao;

import com.bei.domain.User;

public interface UserMapper {
    // int因为返回的是受影响行数
    int addUser(User user);

    User queryUserByMobile(String mobile);
}
