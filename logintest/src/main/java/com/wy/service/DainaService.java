package com.wy.service;

import com.wy.bean.Daina;
import com.wy.dao.DainaMapper;
import com.wy.utils.SigninUtils;
import com.wy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DainaService {
    @Autowired
    DainaMapper mapper;
    @Autowired
    SigninUtils signinUtils;
    @Autowired
    StringUtils stringUtils;
    public void addDainaOrder(Daina daina){
        daina.setId(stringUtils.getID());
        daina.setUserId(signinUtils.getUser().getId());
        //自动设定为未被领取
        daina.setStatus(1);
        System.out.println(daina);
        mapper.addDaina(daina);
    }

    //获取当前用户的所有订单
    public List<Daina> queryAll(){
        return mapper.queryDaina(signinUtils.getUser().getId());
    }

    public void updateDaina(Daina daina){
        mapper.updateDaina(daina);
    }
    //确认订单
    public void sureOrder(String id){
        mapper.sureOrder(id);
    }

    //根据订单id删除订单
    public void delDainaById(String id){
        mapper.deleteDaina(id);
    }

    //获取所有的订单
    public List<Daina> getDainaAll(){
        return mapper.getDainaAll();
    }

    //帮人代拿
    public void putDaina(String id){
        mapper.putDaina(id,signinUtils.getUser().getId());
    }
}
