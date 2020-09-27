package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * mybatis的入门案例
 */
public class MybatisTest {
    /**
     * 入门案例
     * @param args
     */
    public static void main(String[] args) throws Exception {
        /**
         * 1.读取配置文件
         *
         * 读取配置文件可能存在的问题：
         *     绝对路径：d:/xxx/xxx.xml，有的用户可能没有d盘
         *     相对路径：src/java/main/xxx.xml，部署的时候src文件夹就没有了
         * 解决方案：
         *     a. 使用类加载器，但只能读取类路径的配置文件
         *     b. 使用ServletContext对象的getRealPath()可以得到当前应用部署的绝对路径
         *        这个路径是你项目在哪，他就在那
         */
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

        /**
         * 2.创建SQLSessionFactory工厂
         *
         * SqlSessionFactory factory = builder.build(in);
         * 从上面这句可以知道，工厂不是我们自己创建的，创建工厂mybatis使用了构建者模式，
         * builder就是构建者
         *
         * 优势： 把对象的创建细节隐藏，使使用者直接调用方法即可拿到对象，屏蔽了繁琐的操作
         *
         */
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        /**
         * 3.使用工厂生产SqlSession对象 
         *
         * SqlSession session = factory.openSession();
         * 上面这句使用了工厂模式，生产SqlSession
         *
         * 优势：解耦（降低类之间的依赖关系）
         *
         * 如果不使用工厂模式：
         * 假设有
         * SqlSession session = new 实现1();
         * 当有天想换成实现2时：
         * SqlSession session = new 实现2();
         * 需要修改代码，应用都是web开发，都是部署到服务器上的，如果每次修改都要改源码，
         * 每次修改都需要重新编译、重新部署
         */
        SqlSession session = factory.openSession();

        /**
         * 4.使用SqlSession创建Dao接口的代理对象
         *
         * 代理模式：
         *
         * 优势：不修改源码的基础上对已有方法增强
         *
         * 通过创建dao接口的实现类的代理对象，从而实现了不写dao实现类也能实现功能
         */
        IUserDao userDao = session.getMapper(IUserDao.class);

        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user:users) {
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }
}
