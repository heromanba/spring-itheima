package com.itheima.ui;


import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;

/**
 * 模拟一个表现层，用于调用业务层，在实际开发中这应该是一个servlet。
 */
public class Client {

    public static void main(String[] args) {
         IAccountService as = new AccountServiceImpl();
         as.saveAccount();
    }
}
