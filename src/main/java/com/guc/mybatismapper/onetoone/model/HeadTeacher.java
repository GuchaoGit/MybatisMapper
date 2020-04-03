package com.guc.mybatismapper.onetoone.model;

/**
 * @Author guc
 * @Date 2020/4/3 10:45
 * @Description 教师
 */
public class HeadTeacher {
    private Integer id;
    private String name;
    private Integer age;

    public HeadTeacher() {

    }

    public HeadTeacher(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
