package com.itheima.ui;


import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;

/**
 * 模拟一个表现层，用于调用业务层，在实际开发中这应该是一个servlet。
 */
public class Client {

    /*
        用于调用业务层的表现层main函数。
        注意这里面的依赖关系：
            业务层调持久层的"new AccountDaoImpl()"
            表现层调业务层的"new AccountServiceImpl()"
        上面的依赖关系是需要解决的，导致了代码的耦合性很高，独立性很差，会产生编译期错误。

        Q：如何去解决上述依赖关系？
        A：工厂模式解耦，但要清楚工厂到底是做什么的
     */
    public static void main(String[] args) {
        IAccountService as = new AccountServiceImpl();
        as.saveAccount();
    }
}
