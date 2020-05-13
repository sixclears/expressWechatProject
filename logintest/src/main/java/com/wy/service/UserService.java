package com.wy.service;

import com.wy.bean.User;
import com.wy.dao.UserMapper;
import com.wy.utils.SigninUtils;
import com.wy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StringUtils stringUtils;

    @Autowired
    SigninUtils signinUtils;



    /*
    实现登录功能，如果用户第一次登录，将用户的信息存放到数据库
    openId:用户的微信openId
    sessionKey：用户的微信sessionKey
    name：用户的微信名
     */
    public synchronized User loginByOpenId(String openId,String sessionKey,String name){

        //从数据库中查找是否有用户存在
        User user = this.getByOpenid(openId);
        System.out.println("根据openid查询数据库中是否有用户存在"+user);
        if(user==null){
            //user不存在
            user =new User();
            user.setId(stringUtils.getID());
            user.setName(name);
            user.setWxOpenId(openId);
            user.setToken(stringUtils.getID());
            this.add(user);
        }else{
            //如果用户存在，但前台token丢失，则将原来的token键值对删除，保持redis中查出用户的token
            // 的单一性
            String oldtoken = user.getToken();
            System.out.println("oldtoken为"+oldtoken);
            signinUtils.delUser(oldtoken);
            //设置新的token
            user.setToken(stringUtils.getID());
            userMapper.updateToken(user.getToken());
        }
        user.setWxSessionKey(sessionKey);
        //修改后的token存储

        signinUtils.setUser(user);
        System.out.println("进一步封装的user:"+user);
        return user;
    }



    //查询用户
    public User getByOpenid(String openId){
        return userMapper.getByWxOpenId(openId);
    }

    //添加用户到数据库中
    public void add(User user){
        userMapper.add(user);
    }


    //根据token获取用户信息
    public User getUserByToken(){
        return signinUtils.getUser();
    }
}
