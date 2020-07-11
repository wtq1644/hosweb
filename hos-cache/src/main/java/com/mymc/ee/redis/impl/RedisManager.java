package com.mymc.ee.redis.impl;

import com.mymc.ee.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository("redisManager")
public class RedisManager implements RedisCache {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public String get(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }
}
