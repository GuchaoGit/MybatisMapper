package com.guc.mybatismapper.onetoone;

import com.guc.mybatismapper.onetoone.mapper.ClassesMapper;
import com.guc.mybatismapper.onetoone.mapper.StudentMapper;
import com.guc.mybatismapper.onetoone.model.Classes;
import com.guc.mybatismapper.onetoone.model.Course;
import com.guc.mybatismapper.onetoone.model.Student;
import com.guc.mybatismapper.onetoone.model.StudentCourseLink;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
        selectClassAndStudentsById(1);
        selectStudentCourse();
        selectCourseStudent();

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

    private static void selectClassAndStudentsById(int i) {
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();
        ClassesMapper mapper = session.getMapper(ClassesMapper.class);
        try {
            Classes classes = mapper.selectClassAndStudentsById(1);
            session.commit();
            System.out.println("班级信息："+classes.getId()+","+classes.getName());
            List<Student> students = classes.getStudents();
            System.out.println("班级的所有学生信息：");
            for(Student stu:students){
                System.out.println(stu.getId()+","+stu.getName()+","+stu.getSex()+","+stu.getAge());
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        // 释放资源
        session.close();
    }

    //查询所有学生及他们的选择课程的信息
    private static void selectStudentCourse() {
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        StudentMapper mapper = session.getMapper(StudentMapper.class);
        try {
            List<Student> students = mapper.selectStudentCourse();
            session.commit();
            for(Student stu:students){
                System.out.println("学生："+stu.getId()+","+stu.getName()+","+stu.getSex()+","+stu.getAge()+":");
                List<Course> courses = stu.getCourses();
                for(Course cou:courses){
                    System.out.println("课程："+cou.getId()+","+cou.getName()+","+cou.getCredit());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }

        // 释放资源
        session.close();
    }

    // 根据学生 id 和课程 id 删除该学生该门课的选课情况
    private static void deleteStudentCourseById(){
        SqlSession session = sqlSessionFactory.openSession();

        StudentMapper mapper = session.getMapper(StudentMapper.class);
        try {
            Student student = new Student();
            student.setId(1);
            Course course = new Course();
            course.setId(2);
            StudentCourseLink scLink = new StudentCourseLink();
            scLink.setStudent(student);
            scLink.setCourse(course);
            mapper.deleteStudentCourseById(scLink);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }

        session.close();
    }

    private static void selectCourseStudent() {
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        StudentMapper mapper = session.getMapper(StudentMapper.class);
        try {
            List<Course> courses = mapper.selectCourseStudent();
            session.commit();
            for(Course cou:courses){
                System.out.println("课程："+cou.getId()+","+cou.getName()+","+cou.getCredit()+":");
                List<Student> students = cou.getStudents();
                for(Student stu:students){
                    System.out.println("学生："+stu.getId()+","+stu.getName()+","+stu.getSex()+","+stu.getAge());
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
