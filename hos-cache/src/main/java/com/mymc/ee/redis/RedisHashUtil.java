package com.mymc.ee.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Hash缓存工具类
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@Repository
public class RedisHashUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 存储单个值至map中
     * @param redisKey redisKey中的key
     * @param mapKey  map所对应的key
     * @param value map所对应的值
     */
    public void addMapOne(String redisKey,String mapKey,Object value) {
        redisTemplate.opsForHash().put(redisKey, mapKey,value);
    }

    /**
     * 存储整个map至redis
     * @param key redis中存储的key
     * @param map 需缓存的Map
     */
    public void addMapAll(String key, Map<String,Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 获取整个HashMap
     * @param redisKey redis中存储的key
     * @return 整个Map
     */
    public Map<String,Object> getMapAll(String redisKey) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(redisKey);
        Map<String, Object> retEntries = new HashMap<>();
        for(Map.Entry<Object , Object> temp:entries.entrySet()){
            Object key = temp.getKey();
            Object value = temp.getValue();
            retEntries.put(String.valueOf(key) , value);
        }
        return retEntries;
    }

    /**
     * 获取redis中hash的所有value
     * @param redisKey
     * @return
     */
    public List<Object> getMapValues(String redisKey) {
        return redisTemplate.opsForHash().values(redisKey);
    }

    /**
     * 删除Map中的某个键值对
     * @param redisKey
     * @param mapKey
     * @return 返回影响数量
     */
    public Long deleteMapVal(String redisKey , Object ... mapKey) {
        return redisTemplate.opsForHash().delete(redisKey , mapKey);
    }
    /**
     * 确定hashkey是否存在
     * @param redisKey redis存储的key
     * @param mapKey 需要确定的map对象key
     * @return
     */
    public boolean hasKey(String redisKey , String mapKey) {
        return	redisTemplate.opsForHash().hasKey(redisKey , mapKey);
    }
    /**
     * 获取Map中具体的值
     * @param redisKey redis存储的key
     * @param mapKey 获取的map对象key
     * @return
     */
    public Object getMapVal(String redisKey, String mapKey) {
        return redisTemplate.opsForHash().get(redisKey,mapKey);
    }

    /**
     * 从哈希中获取给定key的值
     * @param redisKey redis存储的key
     * @param mapKeys 需要去出的key的集合
     * @return 值列表
     */
    public List<Object> multiGetHash(String redisKey , List<Object> mapKeys) {
        return  redisTemplate.opsForHash().multiGet(redisKey , mapKeys);
    }

    /**
     * 获取所有map中的key
     * @param redisKey
     * @return
     */
    public Set<String> getHashKeys(String redisKey) {
        Set<Object> keys = redisTemplate.opsForHash().keys(redisKey);
        Set<String> retKeys = new HashSet<>();
        for (Object key : keys) {
            retKeys.add(String.valueOf(key));
        }
        return retKeys;
    }

    /**
     * 获取所有map中的key的数量
     * @param redisKey redis中的key
     * @return key的数量
     */
    public int getHashSize(String redisKey) {
        Set<Object> keys = redisTemplate.opsForHash().keys(redisKey);
        if(keys == null){
            return 0;
        }
        return keys.size();
    }
}