# MyBatis 映射关系
## 项目创建步骤：  
1、新建项目  
2、修改pom.xml文件  
3、创建方法接口和定义映射文件  
4、配置文件 mybatis.cfg.xml  
5、日志记录log4j.properties  

一对一映射：一个班级一个班主任  
一对多映射：一个班级对应多个学习  

## 动态sql
常用的动态sql元素包括：
* if   if 在 where 子句中做简单的条件判断
* choose(when,otherwise)    choose 相当于 java 语言中的 switch
* trim(where,set)
  * trim 元素可以给自己包含的内容加上前缀（prefix）或加上后缀（suffix），也可以把包含内容的首部（prefixOverrides）或尾部（suffixOverrides）某些内容移除。
  ```
  <select id="dynamicTrimTest" resultType="User">
        select * from user
      <trim prefix="where" prefixOverrides="and |or ">
            <if test="address != null">
              address = #{address}
            </if>
            <if test="phone != null">
              and phone like #{phone}
            </if>
      </trim>
  </select>
  ```
  * where 元素知道只有在一个以上的 if 条件满足的情况下才去插入 where 子句，而且能够智能地处理 and 和 or 条件。
  ```
  <select id="dynamicWhereTest" resultType="User">
        select * from user
      <where>
            <if test="address != null">
              address = #{address}
            </if>
            <if test="phone != null">
              and phone like #{phone}
            </if>
      </where>
  </select>
  ```  
  * set 元素可以被用于动态包含需要更新的列，而舍去其他的。
  ```
  <update id="dynamicSetTest">
        update User
      <set>
            <if test="phone != null">phone=#{phone},</if>
            <if test="address != null">address=#{address}</if>
      </set>
        where id=#{id}
  </update>
  //等价
  <trim prefix="set" suffixOverrides=",">
    ...
  </trim>
  ```
* foreach 元素常用到需要对一个集合进行遍历时，在 in 语句查询时特别有用
* bind

## 关于参数的问题
  * 使用#{}来传递参数
  * 若目标方法的参数类型为对象类型，则调用其对应的getter方法，如getEmail()
  * 若目标方法的参数类型为Map类型，则调用其get(key)
  * 若参数是单个的，或者列表，需要使用@param注解来进行标记
  ```
  public void updateEmp(@Param("id")Integer id,
                        @Param("name")String name,
                        @Param("password")String password,
                        @Param("email")String email);
                        
  <update id="updateEmp">
        update student set name=#{name},password=#{password},email=#{email} where id=#{id}
  </update>
  ```
  * 注意：若只有一个参数，则可以省略@param注解

## 参数值的获取
  * ·#{}：可以获取map中的值或者pojo对象属性的值  
  是以预编译的形式，将参数设置到sql语句中，相当于PreparedStatement;防止sql注入
  * ${}: 可以获取map中的值获取pojo对象属性的值  
  取出的值直接拼装在sql语句中，会有安全问题  
  #### 注：表名不支持预编译，排序不支持预编译，只能用${}
  
## 数据库列名与实体类属性名不对应的处理方式
  * sql语句用as 换名
  * 下划线转换成驼峰式命名  
  在全局配置文件中  
  * 利用ResultMap
