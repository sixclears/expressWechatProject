package com.wy.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wy.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
/**
 * 用于获取当前用户登录状态，登录身份，封装、代替session操作
 * 
 * @author xuesinuo
 *
 */
@Component
public class SigninUtils {
    @Autowired
    HttpSession session;
    @Autowired
    WebRequest request;

    @Autowired
    StringUtils stringUtils;

    @Autowired
    RedisTemplate<Object,User> userRedisTemplate;

    //存储token信息，视为缓存机制
//    Map<String, String> userToToken = new ConcurrentHashMap<>();
//    Map<String, User> tokenToUser = new ConcurrentHashMap<>();

    public User getUser() {
        // return (User) session.getAttribute(SessionName.USER);
        //将用户的信息存放在请求头中
        String token = request.getHeader("token");
        System.out.println("从小程序传过来的token值为："+token);
        //将map集合作为缓存的机制中，不会出现缓存过期的现象，故不需要判断token的一致性
        if (token != null) {
            User user =(User) userRedisTemplate.opsForValue().get(token);
            System.out.println("根据token得到的user为："+user);
            return user;
        }
        return null;
    }

    /**
     * 设置当前登录的User身份,存入缓存
     * 
     * @param user 用户的信息
     */
    public synchronized void setUser(User user) {
        userRedisTemplate.opsForValue().set(user.getToken(),user);//将user存入redis
    }


    /**
     * 获得用户IP
     * 
     * @param request
     * @return
     */
    public static String getCliectIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ip.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ip = str;
                break;
            }
        }
        return ip;
    }
    public void delUser(String oldToken){
        userRedisTemplate.delete(oldToken);
        //
    }
}
