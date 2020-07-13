package com.mymc.ee.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 普通值缓存工具类
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@Repository
public class RedisValueUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取 String 类型 key-value
     * @param key 键
     * @return
     */
    public String get(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }

    /**
     * 带失效时间设置
     * @param key 键
     * @param value 值
     * @param timeout 失效时间（秒）
     */
    public void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS );
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void delete(Collection<String> keys){
        redisTemplate.delete(keys);
    }

    /**
     * 为多个键分别设置他们的值
     * 使用：Map<String,String> maps = new HashMap<String, String>();
     maps.put("multi1","ccc");
     maps.put("multi2","xxx");
     maps.put("multi3","zzz");
     template.opsForValue().multiSet(maps);
     * @param map
     */
    protected void multiSetObj(Map<String,Object> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    /***
     * 分多个键同时取出他们的值
     * List<String> keys = new ArrayList<String>();
     keys.add("multi1");
     keys.add("multi2");
     keys.add("multi3");
     System.out.println(template.opsForValue().multiGet(keys));
     结果：[ccc, xxx, zzz]
     * @param keys
     *
     */
    protected List<Object> multiGetObj(List<String> keys) {
        return  redisTemplate.opsForValue().multiGet(keys);
    }
}

