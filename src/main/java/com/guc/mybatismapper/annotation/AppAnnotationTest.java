package com.guc.mybatismapper.annotation;

import com.guc.mybatismapper.annotation.mapper.User2Mapper;
import com.guc.mybatismapper.annotation.model.User2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author guc
 * @Date 2020/4/8 10:41
 * @Description 注解测试
 */
public class AppAnnotationTest {
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
//        insertUser();
//        updateUser();
//        deleteUser();
        selectAllUser();
        selectUserById(11);
    }


    /**
     * 插入用户
     */
    private static void insertUser() {
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        User2Mapper mapper = session.getMapper(User2Mapper.class);
        User2 user = new User2();
        user.setUsername("Guc");
        user.setSex("male");
        user.setAddress("zhengzhou");
        user.setPassword("123456");
        try {
            mapper.insert(user);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }

        // 释放资源
        session.close();
    }

    /**
     * 更新用户信息
     */
    public static void updateUser(){
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();
        User2Mapper mapper = session.getMapper(User2Mapper.class);
        try {
            mapper.update(11,"JiaoZuo");
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        // 释放资源
        session.close();

    }
    /**
     * 删除用户信息
     */
    public static void deleteUser(){
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();
        User2Mapper mapper = session.getMapper(User2Mapper.class);
        try {
            mapper.deleteUser(10);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        // 释放资源
        session.close();

    }

    /**
     * 查询所有用户信息
     */
    public static void selectAllUser(){
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();
        User2Mapper mapper = session.getMapper(User2Mapper.class);
        try {
            List<User2> users = mapper.selectAllUser();
            session.commit();
            if (users!=null){
                for (User2 user:users){
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

    /**
     * 根据id 查询用户信息
     * @param i
     */
    private static void selectUserById(Integer i) {
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();
        User2Mapper mapper = session.getMapper(User2Mapper.class);
        try {
            User2 user = mapper.selectUserById(i);
            session.commit();
            if (user!=null){
                System.out.println(user.getId()+" "
                        + user.getUsername()+" "
                        + user.getPassword()+" "
                        + user.getSex()+" "
                        + user.getAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        // 释放资源
        session.close();
    }
}
