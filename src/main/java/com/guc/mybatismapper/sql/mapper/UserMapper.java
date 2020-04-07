package com.guc.mybatismapper.sql.mapper;

import com.guc.mybatismapper.sql.model.User;

import java.util.List;

public interface UserMapper {

    public List<User> dynamicIfTest(User user) throws Exception;

    public List<User> dynamicChooseTest(User user) throws Exception;

    public List<User> dynamicTrimTest(User user) throws Exception;

    public List<User> dynamicForeachTest(List<Integer> ids) throws Exception;

    public List<User> dynamicBindTest(User  user) throws Exception;
}
