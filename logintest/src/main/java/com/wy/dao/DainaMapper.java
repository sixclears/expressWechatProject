package com.wy.dao;

import com.wy.bean.Daina;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Mapper
public interface DainaMapper {

    //将代拿信息存放在数据库中
    @Insert("insert into `daina` (`id`,`user_id`,`name`,`expressNumber`,`tip`,`address`,`myAddress`,`salary`,`expressType`,`who`,`tel`,`status`)" +
            " values (#{id},#{userId},#{name},#{expressNumber},#{tip},#{address},#{myAddress},#{salary},#{expressType},#{who},#{tel},#{status})")
    public void addDaina(Daina daina);

    //设置谁代拿了
    @Update("update `daina` set `who`=#{who} where `user_id`=#{userId}")
    public void updateWho(String who,String userId);

    //根据代拿订单的id号删除代拿信息
    @Delete("delete from daina where id=#{id}")
    public void deleteDaina(String id);

//    //根据id更新代拿订单详情
//    @Update("update `daina` set `name`=#{name},`expressNumber`=#{expressNumber},`tip`=#{tip},`address`=#{address},`myAddress`=#{myAddress},`expressType`=#{expressType},`tel`=#{tel} " +
//            "where `id`=#{id}")
    public void updateDaina(Daina daina);

    //根据userId查出所有订单
    @Select("select * from daina where `user_id`=#{userId}")
    public List<Daina> queryDaina(String userId);

    //设置谁代拿了
    @Update("update `daina` set `status`= 3 where `id`=#{id}")
    public void sureOrder(String id);

    //获取所有的订单
    @Select("select * from `daina`")
    public List<Daina> getDainaAll();

    //帮人代拿
    @Update("update `daina` set `who`=#{who},`status`=2 where id=#{id}")
    public void putDaina(String id,String who);

}
