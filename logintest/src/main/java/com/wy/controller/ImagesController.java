package com.wy.controller;

import com.wy.bean.Image;
import com.wy.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ImagesController {

    @Autowired
    ImagesService imagesService;
    //获取相同位置的图片
    @GetMapping("/images/{page}")
    public List<Image> getImagesByPage(@PathVariable("page") String page){

        List<Image> images = imagesService.getImagesByPage(page);

        return images;
    }

}
