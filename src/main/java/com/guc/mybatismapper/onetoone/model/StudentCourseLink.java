package com.guc.mybatismapper.onetoone.model;

import java.util.Date;

/**
 * @Author guc
 * @Date 2020/4/3 15:22
 * @Description 学生和课程之间的关系
 */
public class StudentCourseLink {
    private Student student;
    private Course course;
    private Date date;
    public StudentCourseLink() {

    }

    public StudentCourseLink(Student student, Course course, Date date) {
        this.student = student;
        this.course = course;
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
