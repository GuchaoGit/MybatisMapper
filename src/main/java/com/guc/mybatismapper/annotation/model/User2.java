package com.guc.mybatismapper.annotation.model;

/**
 * @Author guc
 * @Date 2020/4/8 10:43
 * @Description 用户
 */
public class User2 {
    private Integer id; // id，主键
    private String username; // 用户名
    private String password; // 密码
    private String sex; // 性别
    private String address; // 地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
