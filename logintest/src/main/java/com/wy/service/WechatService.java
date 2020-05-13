package com.wy.service;

import com.wy.config.wechat.WechatConfig;
import com.wy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WechatService {

    //内置的httpclient类
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WechatConfig wechatConfig;

    /*
    到微信服务器获取用户的信息
     */

    @Autowired
    StringUtils stringUtils;
    public Map<String,Object> code2Session(String code){
        /*
        code2SessionUrl：根据code拼接微信开放api
         */
        String result =restTemplate.getForObject(wechatConfig.code2SessionUrl(code),String.class);
        /*
        将得到的json结果转化成Map集合
         */
        Map<String,Object> wechatResult =stringUtils.Json2Map(result);
        return wechatResult;
    }
}
