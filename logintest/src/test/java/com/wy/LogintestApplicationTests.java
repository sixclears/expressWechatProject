package com.wy;

import com.wy.bean.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class LogintestApplicationTests {

    //进行字符串形式的键值对操作
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //进行object类型的键值对操作
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        Image image = new Image("test","test-page","test-src");
        redisTemplate.opsForValue().set("test",image);
        System.out.println("从缓存中获取存入reids的值");
        Object test = redisTemplate.opsForValue().get("test");
        System.out.println(test);
    }

}
