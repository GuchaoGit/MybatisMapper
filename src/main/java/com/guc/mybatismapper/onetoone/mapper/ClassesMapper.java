package com.guc.mybatismapper.onetoone.mapper;

import com.guc.mybatismapper.onetoone.model.Classes;

/**
 * 班级方法接口
 */
public interface ClassesMapper extends BaseMapper<Classes> {

    /*
     * 根据 id 查询班级 Classes 和它的学生
     * @param id
     * @return
     * @throws Exception
     */
    public Classes selectClassAndStudentsById(Integer id) throws Exception;
}
