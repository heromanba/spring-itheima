package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
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
    private IAccountDao accountDao = new AccountDaoImpl();

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
