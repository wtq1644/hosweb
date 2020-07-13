package com.mymc.ee.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * List缓存工具类
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@Repository
public class RedisListUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 在list集合最前面插入数据
     * @param key  需要操作的key
     * @param values 待添加的值
     * @return
     */
    public Long insert(String key, Object ... values) {
        return redisTemplate.opsForList().leftPushAll(key ,values);
    }

    /**
     * 在list集合最后面追加数据
     * @param key  需要操作的key
     * @param values 待添加的值
     * @return
     */
    public Long append(String key, Object... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 在指定索引处添加数据，如果存在则为替换
     * @param key 需要操作的key
     * @param index 指定索引，该索引从0开始
     * @param value 待添加的值
     */
    public void set(String key, long index, Object value) {
        redisTemplate.opsForList().set(key , index ,value);
    }

    /**
     * 取出某个范围的集合数据
     * @param key 需要操作的key
     * @param start 集合起点，索引从0开始
     * @param end 集合终点，若为-1则表示取出所有
     * @return 返回所有对象
     */
    public List<Object> getList(String key, long start, long end) {
        return redisTemplate.opsForList().range(key , start , end);
    }

    /**
     * 返回list集合的大小
     * @param key 需要操作的key
     * @return
     */
    public Long size(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 返回某个索引处的数据
     * @param key  需要操作的key
     * @param index 索引 下标从0开始
     * @return
     */
    public Object getByIndex(String key, long index) {
        return redisTemplate.opsForList().index(key , index);
    }

    /**
     * 弹出最左边数据，弹出后集合将不存在该数据
     * @param key
     * @return
     */
    public Object popLeft(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 弹出最右边数据，弹出后集合将不存在该数据
     * @param key
     * @return
     */
    public Object popRight(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 直接删除数据，该数据从某个位置开始
     * @param key   删除的key
     * @param count
     *          大于0，表示删除从左往右数，值为value的第一个数据
     *          小于0，表示删除从右往左数，值为value的第一个数据
     *          等于0，表示删除值为value的所有数据
     * @param value 被删除的数据
     * @return
     */
    public Long remove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count , value);
    }

}
