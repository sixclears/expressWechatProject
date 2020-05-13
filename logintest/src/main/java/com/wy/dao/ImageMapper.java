package com.wy.dao;


import com.wy.bean.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageMapper {

    //根据图片的页面位置获取相同位置的图片
    @Select("select * from imageTable where page=#{page}")
    public List<Image> getImagesByPage(String page);


    //根据name获取图片
    @Select("select * from imageTable where name=#{name}")
    public Image getImageByname(String name);
}
