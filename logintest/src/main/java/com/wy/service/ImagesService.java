package com.wy.service;

import com.wy.bean.Image;
import com.wy.dao.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesService {

    @Autowired
    ImageMapper imageMapper;


    //获取相同位置的图片
    public List<Image> getImagesByPage(String page){

        List<Image> images = imageMapper.getImagesByPage(page);
        return images;
    }

}
