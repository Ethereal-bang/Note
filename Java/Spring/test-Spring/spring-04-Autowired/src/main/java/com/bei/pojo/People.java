package com.bei.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class People {
    @Autowired(required = false)    // required=false允许对象为null
    private Dog dog;

    @Autowired
    @Qualifier(value = "cat")
    private Cat cat;

    private String str;

    public Cat getCat() {
        return cat;
    }
    public Dog getDog() {
        return dog;
    }
    public String getStr() {
        return str;
    }
}