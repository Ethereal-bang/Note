package com.bei.pojo;

// Test:构造器注入
public class User {
    public String name;

    public User() {
        System.out.println("User的无参构造");
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {  // 无参构造函数的类传值时要使用
        this.name = name;
    }

    public void show() {
        System.out.println("name = " + name);
    }
}
