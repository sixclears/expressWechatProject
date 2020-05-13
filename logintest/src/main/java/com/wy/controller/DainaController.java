package com.wy.controller;

import com.wy.bean.Daina;
import com.wy.entity.ResultEntity;
import com.wy.service.DainaService;
import com.wy.utils.SigninUtils;
import com.wy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/daina")
public class DainaController {
    @Autowired
    DainaService dainaService;

    @PostMapping
    public ResultEntity addDainaOrder(Daina daina){
        dainaService.addDainaOrder(daina);
        return ResultEntity.success();
    }

    //获取用户个人的所有的代拿订单
    @GetMapping
    public ResultEntity<List<Daina>> queryAll(){
        List<Daina> dainaList = dainaService.queryAll();
        return ResultEntity.success(dainaList);
    }
    //获取所有的订单
    @GetMapping("/all")
    public ResultEntity<List<Daina>> getDainaAll(){
        List<Daina> all = dainaService.getDainaAll();
        return ResultEntity.success(all);
    }

    @PutMapping
    public ResultEntity updateDaina(Daina daina){
        System.out.println(daina);
        dainaService.updateDaina(daina);
        return ResultEntity.success();
    }

    @PutMapping("/one")
    public ResultEntity sureOrder(String id){
        System.out.println(id);
        dainaService.sureOrder(id);
        return ResultEntity.success();
    }

    @DeleteMapping
    public ResultEntity delDainaById(String id){
        System.out.println(id);
        dainaService.delDainaById(id);
        return ResultEntity.success();
    }

    //帮人代拿
    @PutMapping("/put")
    public ResultEntity putDaina(String id){
        System.out.println(id);
        dainaService.putDaina(id);
        return ResultEntity.success();
    }
}
