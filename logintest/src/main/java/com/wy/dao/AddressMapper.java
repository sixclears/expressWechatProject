package com.wy.dao;

import com.wy.bean.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper {

    //保存用户的个人地址
    @Insert("INSERT INTO `address` (`id`, `user_id`, `name`, `tel`, `address`)" +
            " VALUES (#{id}, #{userId}, #{name}, #{tel}, #{address})")
    public void add(Address address);

    //根据userId查询用户的所有个人地址
    @Select("select * from address where user_id=#{userId}")
    public List<Address> getAddressByUserId(String userId);

    //根据id查询单条地址
    @Select("select * from address where id=#{id}")
    public Address getAddressById(String id);

    //更新用户的个人地址
    @Update("update address set name=#{name},tel=#{tel},address=#{address} where id=#{id}")
    public void updateById(Address address);


    //删除个人地址
    @Delete("delete from address where id=#{id}")
    public void delById(String id);

    //根据用户id删除所有地址
    @Delete("delete from address where user_id=#{userId}")
    public void delByUserId(String userId);

}
