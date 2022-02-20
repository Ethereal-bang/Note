package com.bei.controller;

import com.bei.domain.User;
import com.bei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {
    // controller调service层
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    // 注册
    @RequestMapping("/register")
    public String register(User user, Model model) {
        System.out.println(user.getMobile());
        // 由得到的出生日期计算得出age
        user.setAge(Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(user.getBirthday().substring(0, 4)));

        User queryUser = userService.queryUserByMobile(user.getMobile());
        if (queryUser == null) {// 用户不存在，注册成功
            userService.addUser(user);
            return "success";
        } else {
            System.out.println(queryUser.getPassword() + " " + user.getPassword());
            if (!Objects.equals(queryUser.getPassword(), user.getPassword())) {    // 密码错误
                return "error";
            } else {
                return "success";
            }
        }
    }

    // 登录
    @RequestMapping("/login")
    public String login(String mobile, String password) {
        User queryUser = userService.queryUserByMobile(mobile);
        if (queryUser == null) {// 用户不存在
            return "error";
        } else {
            System.out.println(queryUser.getPassword() + " " + password);
            if (!Objects.equals(queryUser.getPassword(), password)) {    // 密码错误
                return "error";
            } else {
                return "success";
            }
        }
    }
}
