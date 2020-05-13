package com.wy.config;


import com.wy.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, User> userRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
    throws UnknownHostException {

        RedisTemplate<Object, User> template = new RedisTemplate<Object, User>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
        return template;
    }

}
