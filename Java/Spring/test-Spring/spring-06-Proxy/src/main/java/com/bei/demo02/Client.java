package com.bei.demo02;

// 客户类——租房客户，找代理
public class Client {
    public static void main(String[] args) {
        // 真实角色
        Host host = new Host();

        // 代理
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        // 通过调用程序处理角色来处理要调用的接口对象
        pih.setRent(host);
        Rent proxy = (Rent) pih.getProxy();
        proxy.rent();
    }
}

