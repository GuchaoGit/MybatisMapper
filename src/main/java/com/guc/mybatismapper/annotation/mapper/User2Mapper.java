package com.guc.mybatismapper.annotation.mapper;

import com.guc.mybatismapper.annotation.model.User2;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface User2Mapper {
    /**
     * 新增用户
     * @param user
     * @return
     * @throws Exception
     */
    @Insert("insert into user(username,password,sex,address) values(#{username},#{password},#{sex},#{address})")
    @Options(useGeneratedKeys=true,keyProperty="id")
    public int insert(User2 user) throws Exception;

    @Update("update user set address=#{address} where id=#{id}")
    public void update(@Param("id") Integer id,@Param("address") String address) throws Exception;

    /**
     * 删除用户
     * @param id
     * @return
     * @throws Exception
     */
    @Delete("delete from user where id=#{user_id}")
    public int deleteUser(Integer id) throws Exception;


    /*
     * 查询所有用戶
     * @return
     * @throws Exception
     */
    @Select("select * from user")
    public List<User2> selectAllUser() throws Exception;


    /*
     * 根据 id 查询用戶
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select u.id,u.username,u.password as pwd,u.sex,u.address from user u where id=#{id}")
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="username",column="username"),
            @Result(property="password",column="pwd"),//列名跟属性名不一致处理方法
            @Result(property="sex",column="sex"),
            @Result(property="address",column="address"),
    })
    public User2 selectUserById(Integer id) throws Exception;
}
