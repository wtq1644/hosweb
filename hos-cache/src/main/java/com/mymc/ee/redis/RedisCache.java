package com.mymc.ee.redis;

import java.util.Map;
import java.util.concurrent.TimeUnit;


public interface RedisCache {
    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    boolean set(String key, Object value);

    /**
     * 获取 String 类型 key-value
     * @param key
     * @return
     */
    String get(String key);
}
