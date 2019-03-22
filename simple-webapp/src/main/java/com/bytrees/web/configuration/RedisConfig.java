package com.bytrees.web.configuration;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.bytrees.cache.redis.ObjectRedisSerializer;

@Configuration
public class RedisConfig {
	@Bean
    public RedisTemplate<Serializable, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<Serializable, Object> template = new RedisTemplate<Serializable, Object>();
		template.setConnectionFactory(connectionFactory);
		template.afterPropertiesSet();
		//设置redis key的序列化器
		template.setKeySerializer(new StringRedisSerializer());
		//设置redis value的序列化器
		template.setValueSerializer(new ObjectRedisSerializer(new SerializingConverter(), new DeserializingConverter()));
		return template;
    }
}
