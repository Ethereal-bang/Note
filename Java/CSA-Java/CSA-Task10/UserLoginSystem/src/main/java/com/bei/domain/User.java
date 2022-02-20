package com.bei.domain;

import com.bei.dao.UserMapper;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
// 前两字段负责登录
    private String mobile;
    private String password;

    private int age;
    private String birthday;
}
