package com.guc.mybatismapper.sql;

import com.guc.mybatismapper.sql.mapper.UserMapper;
import com.guc.mybatismapper.sql.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author guc
 * @Date 2020/4/7 9:38
 * @Description 动态sql测试
 */
public class AppDynamicSql {
    private static SqlSessionFactory sqlSessionFactory;
    public static void main(String[] args) {
        // Mybatis 配置文件
        String resource = "mybatis.cfg.xml";
        // 得到配置文件流
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建会话工厂，传入 MyBatis 的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        dynamicIfTest();
//        dynamicChooseTest();
//        dynamicTrimTest();
//        dynamicForeachTest();
        dynamicBindTest();
    }


    //if 在 where 子句中做简单的条件判断
    private static void dynamicIfTest() {
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            User userParam = new User();
//            userParam.setAddress("beijing");
            userParam.setUsername("%o%");
            List<User> users = mapper.dynamicIfTest(userParam);
            session.commit();
            if (users!=null){
                for (User user:users){
                    System.out.println(user.getId()+" "
                            + user.getUsername()+" "
                            + user.getPassword()+" "
                            + user.getSex()+" "
                            + user.getAddress()+" "
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        // 释放资源
        session.close();
    }

    private static void dynamicChooseTest() {
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            User userParam = new User();
//            userParam.setUsername("%o%");
            List<User> users = mapper.dynamicChooseTest(userParam);
            session.commit();
            if (users!=null){
                for (User user:users){
                    System.out.println(user.getId()+" "
                            + user.getUsername()+" "
                            + user.getPassword()+" "
                            + user.getSex()+" "
                            + user.getAddress()+" "
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        // 释放资源
        session.close();
    }
    //trim 元素可以给自己包含的内容加上前缀（prefix）或加上后缀（suffix），也可以把包含内容的首部（prefixOverrides）或尾部（suffixOverrides）某些内容移除。
    private static void dynamicTrimTest() {
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            User userParam = new User();
            userParam.setUsername("%o%");
//            userParam.setAddress("beijing");
            List<User> users = mapper.dynamicTrimTest(userParam);
            session.commit();
            if (users!=null){
                for (User user:users){
                    System.out.println(user.getId()+" "
                            + user.getUsername()+" "
                            + user.getPassword()+" "
                            + user.getSex()+" "
                            + user.getAddress()+" "
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        // 释放资源
        session.close();
    }

    //foreach 元素常用到需要对一个集合进行遍历时，在 in 语句查询时特别有用。
    private static void dynamicForeachTest() {
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            List<Integer> ids = new ArrayList<>();
            ids.add(7);
            ids.add(9);
            List<User> users = mapper.dynamicForeachTest(ids);
            session.commit();
            if (users!=null){
                for (User user:users){
                    System.out.println(user.getId()+" "
                            + user.getUsername()+" "
                            + user.getPassword()+" "
                            + user.getSex()+" "
                            + user.getAddress()+" "
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        // 释放资源
        session.close();
    }

    private static void dynamicBindTest() {
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            User userParam = new User();
            userParam.setUsername("o");
            List<User> users = mapper.dynamicBindTest(userParam);
            session.commit();
            if (users!=null){
                for (User user:users){
                    System.out.println(user.getId()+" "
                            + user.getUsername()+" "
                            + user.getPassword()+" "
                            + user.getSex()+" "
                            + user.getAddress()+" "
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        // 释放资源
        session.close();
    }
}
