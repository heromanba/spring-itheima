package com.itheima.ui;


import com.itheima.factory.BeanFactory;
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
        // IAccountService as = new AccountServiceImpl();

        /*
            此代码中仍存在问题，下面的代码会打印出5个不同的对象（多例）
            多例：对象被创建多次，执行效率没有单例高
            单例：对象只被创建一次，类中的成员也只会初始化一次

            service和dao中不存在成员变量，所以不存在线程问题，可以把变量初始化在方法中，
            因为每次方法都会初始化，所以也能保证都是不同的变量。

            业务层和持久层很少会包含可以修改的类成员的。
        */
        for (int i = 0; i < 5; i++) {
            IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
            System.out.println(as);
        }

        // as.saveAccount();
    }
}
