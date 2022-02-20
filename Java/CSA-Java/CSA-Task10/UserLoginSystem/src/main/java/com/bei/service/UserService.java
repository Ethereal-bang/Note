package com.bei.service;

import com.bei.domain.User;

public interface UserService {
    int addUser(User user);

    User queryUserByMobile(String mobile);
}
