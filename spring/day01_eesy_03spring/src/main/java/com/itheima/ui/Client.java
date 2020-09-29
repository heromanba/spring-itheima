package com.itheima.ui;


import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.security.cert.X509Certificate;

/**
 * 模拟一个表现层，用于调用业务层，在实际开发中这应该是一个servlet。
 */
public class Client {

    /**
     * 获取spring的IoC核心容器，并根据id获取对象
     *
     * ApplicationContext的三个常用实现类：
     *     1. ClassPathXmlApplicationContext：
     *         可以加载类路径下的配置文件，要求配置文件必须在类路径下，不在的话，加载不了。
     *     2. FileSystemXmlApplicationContext：
     *         可以加载磁盘任意路径下的配置文件（必须有访问权限）。
     *     3. AnnotationConfigApplicaitonContext：
     *         用于读取注解创建容器。
     *
     * 核心容器的两个接口引发出的问题：
     *     1. ApplicationContext：
     *         构建核心容器时，创建对象采用的策略是立即加载，即只要一读取完配置文件马上就创建配置文件中配置的对象。
     *         **单例**创建适用
     *     2. BeanFactory：
     *         构建核心容器时，创建对象采用的策略是延迟加载，即什么时候根据id获取对象了，什么时候才真正的创建对象。
     *         **多例**创建适用
     * @param args
     */
    public static void main(String[] args) {
        /*//1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据id获取Bean对象
        IAccountService as = (IAccountService) ac.getBean("accountService");
        IAccountDao adao = ac.getBean("accountDao", IAccountDao.class);

        System.out.println(as);
        System.out.println(adao);
        as.saveAccount();*/

        //---------BeanFactory----------
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        IAccountService as = (IAccountService) factory.getBean("accountService");
        System.out.println(as);
    }
}
