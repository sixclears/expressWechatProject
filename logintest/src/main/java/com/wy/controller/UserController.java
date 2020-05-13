package com.wy.controller;

import com.sun.deploy.net.HttpRequest;
import com.wy.bean.User;
import com.wy.config.wechat.WechatConfig;
import com.wy.entity.ResultCode;
import com.wy.entity.ResultEntity;
import com.wy.service.UserService;
import com.wy.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import com.wy.config.wechat.WecahtCode;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wechat")
public class UserController {
    @Autowired
    WechatService wechatService;
    @Autowired
    WechatConfig wechatConfig;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResultEntity<User> wecharLogin(String code, String name){
        /*
         *根据小程序提供的code请求微信服务器返回用户的个人信息，如openId和sessionKey
         */
        Map<?,?> wechatResult = wechatService.code2Session(code);

        /*
        排查错误
         */
        if (wechatResult.get("errcode") != null && (Integer) wechatResult.get("errcode") != WecahtCode.SUCCESS) {
            switch ((Integer) wechatResult.get("errcode")) {
                case WecahtCode.BUSY:
                    return ResultEntity.error(ResultCode.WECHAT_ERROR, "微信服务器繁忙");
                case WecahtCode.ERROR_CODE:
                    return ResultEntity.error(ResultCode.WECHAT_ERROR, "错误的微信CODE");
                case WecahtCode.TOO_FREQUENT:
                    return ResultEntity.error(ResultCode.WECHAT_ERROR, "请求过于频繁");
                default:
                    return ResultEntity.error(ResultCode.WECHAT_ERROR, "未知的微信错误");
            }
        }
        System.out.println("openid:"+wechatResult.get("openid"));
        System.out.println("session_key:"+wechatResult.get("session_key"));
        /*
        登录功能
         */
        User user = userService.loginByOpenId((String) wechatResult.get("openid"),
                (String) wechatResult.get("session_key"), name);
        //将user信息封装到带有状态码和状态信息的ResultEntity类中
        return ResultEntity.success(user);
    }

    //根据前端提供的token从缓存中查询记录

    @GetMapping("/tokenauth")
    public ResultEntity<User> getUserInfoByToken(){
        //根据userService.getUserByToken()获取的用户封装成信息返回给微信小程序
        ResultEntity<User> resultEntity = ResultEntity.success(userService.getUserByToken());

        return resultEntity;
    }
}
