package com.guc.mybatismapper.onetoone;

import com.guc.mybatismapper.onetoone.mapper.ClassesMapper;
import com.guc.mybatismapper.onetoone.model.Classes;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author guc
 * @Date 2020/4/3 11:00
 * @Description 测试
 */
public class Test {
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
        selectClassById(1);

    }

    private static void selectClassById(int id) {
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        ClassesMapper mapper = session.getMapper(ClassesMapper.class);
        try {
            Classes classes = mapper.selectById(id);
            session.commit();
            System.out.println(classes.getId() + "," + classes.getName() + ",["
                    + classes.getTeacher().getId() + ","
                    + classes.getTeacher().getName() + ","
                    + classes.getTeacher().getAge()+"]");

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        // 释放资源
        session.close();
    }
}
