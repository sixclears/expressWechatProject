package com.wy.config.wechat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WechatConfig {
    /**
     * 微信小程序appid
     */
    @Value("${wechat.mini-app.app-id}")
    private String appId;
    /**
     * 微信小程序appsecret
     */
    @Value("${wechat.mini-app.app-secret}")
    private String appSecret;

    /**
     * 获取微信Openid的请求地址
     * @param code 换取openid钥匙
     * @return
     * https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
     */
    public String code2SessionUrl(String code){
        return "https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+appSecret+"&js_code="+code+"&grant_type=authorization_code";
    }
}
