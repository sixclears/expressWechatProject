package com.wy.dao;

import com.wy.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserMapper {
    @Insert("INSERT INTO `user` (`id`, `name`, `token`, `wx_open_id`, `isdelete`) " +
            "VALUES (#{id}, #{name}, #{token}, #{wxOpenId}, 0)")
    void add(User u);

    @Select("SELECT * FROM `user` WHERE `isdelete`=0 AND `wx_open_id`=#{wxOpenId}")
    User getByWxOpenId(String wxOpenId);

    //更新token
    @Update("update user set token=#{token}")
    void updateToken(String token);
}
