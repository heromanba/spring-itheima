package com.itheima.factory;

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
 */
public class BeanFactory {
}
