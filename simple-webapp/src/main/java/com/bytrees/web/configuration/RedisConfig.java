package com.bytrees.web.configuration;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.bytrees.cache.redis.ObjectRedisSerializer;

@Configuration
public class RedisConfig {
    /**
     * 
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<Serializable, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Serializable, Object> template = new RedisTemplate<>();
        //设置redis连接
        template.setConnectionFactory(connectionFactory);
        //此方法允许bean实例在设置了所有bean属性后执行其总体配置验证和最终初始化。
        template.afterPropertiesSet();
        //设置redis key的序列化器，一般使用字符串
        template.setKeySerializer(new StringRedisSerializer());
        //设置redis value的序列化器
        //value 一般配置为一个对象，需要自定义ObjectRedisSerializer
        template.setValueSerializer(new ObjectRedisSerializer(new SerializingConverter(), new DeserializingConverter()));
        return template;
    }
}

