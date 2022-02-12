package com.bei.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component  // Bean的注解，等价于 <bean id="user" class=".." /> id默认类的小写
public class User {
    public String name = "bei";
    @Value("17")    // 属性注入 <property name="age" value="17" />
    public int age;
}
