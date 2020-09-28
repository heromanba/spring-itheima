package com.itheima.factory;

import java.io.InputStream;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂
 *
 * Q：什么是Bean对象？
 * A：Bean在计算机英语中有可重用组建的含义。
 *
 * Q：什么是可重用组件呢？
 * A：软件开发中一个项目也有很多的类组成，比如业务层、持久层、实体类，都可以看成是
 *    整个项目的组成部分。可重用的部分有：service可以被很多的servlet使用，dao
 *    可以被很多的service使用。如果不同的servlet共用同一个service，那么我们就
 *    说这个service是一个可重用组件、可重用的组成部分。
 *
 * Q：什么是JavaBean？
 * A：JavaBean并不等于实体类，它的范围远大于实体类。实体类只是可重用组件的一部分。
 *    业务层和持久层都可以看作是可重用组建的一部分。JavaBean是用Java语言编写的可
 *    重用组件。
 *
 * Q：创建Bean对象的工厂到底是创建什么呢？
 * A：创建我们的service和dao对象的。
 *
 * Q：如何创建呢？
 * A：
 *     第一个：需要一个配置文件来配置我们的service和dao
 *     第二个：通过读取配置文件中配置的内容，反射创建对象
 *
 *     注：
 *         配置文件至少包含两部分：全限定类名和对应的唯一标识（key，value）
 *
 * Q：如何准备配置文件？
 * A：配置文件可以是xml也可以是properties
 *
 */
public class BeanFactory {
    //定义一个Properties对象
    private static Properties props;

    //使用静态代码块对Properties对象赋值
    static {
        //1.实例化对象
        //只能降低不可能消除new关键字带来的依赖关系
        props = new Properties();
        //2.获取properties文件的流对象
        //不能直接new FileInputStream，因为相对路径和绝对路径都用不了
        InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            props.load(in);
        } catch (Exception e) {
            //一旦发生这个错误，后面就不会运行了，因为没有配置文件，任何对象都创建不出来
            throw new ExceptionInInitializerError("初始化Properties失败！");
        }
    }

    /**
     * 返回类型应写Object或使用泛型，因为如果写死一个具体的返回值则此方法不沟通用。
     * 另外还需要一个参数指明想要得到的类型，根据Bean的名称获取bean对象。
     */
    public static Object getBean(String beanName) {
        Object bean = null;
        try {
            String beanPath = props.getProperty(beanName);
            bean = Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
