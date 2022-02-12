package com.bei.demo01;

// 客户类——租房客户，找代理
public class Client {
    public static void main(String[] args) {
        //房东要租房
        Host host = new Host();
        //中介帮助房东
        Proxy proxy = new Proxy(host);

        //客户找中介
        proxy.rent();
    }
}
