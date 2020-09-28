package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.factory.BeanFactory;
import com.itheima.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
    /*
        Q：业务层跟谁调用？
        A：根据三层架构，业务层调用持久层

        IAccountDao是接口，AccountDaoImpl是接口的实现类

        注意：
            下面的赋值表达式右边的new关键字，表示编译时依赖，是我们之前提到的要避免的地方。
            这种现象不光存在于业务层调持久层，还存在于表现层调业务层。
    */

    /** 创建对象的两种方式 */
    /*
        1. 采用new创建对象：
        APP ==> 资源
        主动寻找对象，应用app直接和资源联系，并且联系消除不掉，这样对于应用或者资源的独立就会变得很难。
    */
    // private IAccountDao accountDao = new AccountDaoImpl();

    /*
        2. 采用工厂模式：
        APP <== 工厂 ==> 资源
        APP已经和资源断开了联系，而是找工厂要资源，然后由工厂去要资源再把对象转到应用中来，这就是IoC。

        Q：为什么叫IoC（控制翻转）而不叫降低依赖呢？
        A：上面的代码是的AccountServiceImpl拥有完全的自主权去找想要的dao，下面的代码将这种控制权交给了一个叫
           BeanFactory的类，有BeanFactory根据特定的名称找到想要的对象。但是这个对象是不是你能用的，对于
           AccountServiceImpl来说，他已经无法得知了。因为这个工厂能得到哪个bean对象是根据配置所对应的全限定
           类名决定的，这个类本身无法独立自主的控制。这种控制权的转移就叫控制反转。好处是削减计算机程序的耦合。
    */
    private IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
