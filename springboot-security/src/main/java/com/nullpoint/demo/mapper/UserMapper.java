package com.nullpoint.demo.mapper;

import com.nullpoint.demo.entiy.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author nullpoint
 * @date 2020/6/11
 * @desc
 */

@Mapper
public interface UserMapper {

    /**
     * select user by id
     * @param id
     * @return
     */
    @Select("select * from user u where u.id = #{id} ")
    public User selectUserById(@Param("id") Integer id);

    /**
     * select user by username
     * @param username
     * @return
     */
    @Select("select * from user u where u.username = #{username}")
    public User selectUserByName(@Param("username") String username);
}
