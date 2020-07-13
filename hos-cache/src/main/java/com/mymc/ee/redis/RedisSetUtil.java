package com.mymc.ee.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Set集合缓存工具类
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@Repository
public class RedisSetUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 向set中添加元素
     * @param key  需要操作的key
     * @param values 返回添加的个数
     * @return
     */
    public Long add(String key, Object ... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 获取set的size大小
     * @param key 键
     * @return
     */
    public Long size(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 判断 member 元素是否是集合 key 的成员
     * @param key  需要操作的key
     * @param member 待确认的元素
     * @return
     */
    public Boolean hasMember(String key , Object member){
        return redisTemplate.opsForSet().isMember(key , member);
    }

    /**
     * 返回集合中的所有成员
     * @param key
     * @return
     */
    public Set<Object> getAll(String key){
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取key无序集合中的一个元素
     * @param key
     * @return
     */
    public Object randomMember(String key){
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 随机获取多个无序集合中的元素
     * @param key
     * @param count 获取的个数
     * @return
     */
    public List<Object> randomMembers(String key, long count){
        return redisTemplate.opsForSet().randomMembers(key , count);
    }

    /**
     * 随机获取多个无序集合中的元素（去重）
     * @param key
     * @param count 获取的个数
     * @return
     */
    public Set<Object> distinctRandomMembers(String key, long count){
        return redisTemplate.opsForSet().distinctRandomMembers(key , count);
    }

    /**
     * 遍历set
     * @param key
     * @return
     */
    public Cursor<Object> scan(String key){
        return redisTemplate.opsForSet().scan(key , ScanOptions.NONE);
        /*
            while(curosr.hasNext()){
                System.out.println(curosr.next());
            }
         */
    }

    /**
     * 移除集合中一个或多个成员
     * @param key
     * @param values
     * @return
     */
    public Long remove(String key, Object... values){
        return redisTemplate.opsForSet().remove(key ,values);
    }

    /**
     * 将 member 元素从 source 集合移动到 destination 集合
     * @param key
     * @param value
     * @param destKey
     * @return
     */
    public Boolean move(String key, Object value, String destKey){
        return redisTemplate.opsForSet().move(key ,value , destKey);
    }

    /**
     * 交集：返回2个集合的交集
     * @param key
     * @param otherKey
     * @return
     */
    public Set<Object> intersect(String key, String otherKey){
        return redisTemplate.opsForSet().intersect(key , otherKey);
    }

    /**
     * 交集：返回1个key和其他多个key的交集
     * @param key
     * @param otherKeys
     * @return
     */
    public Set<Object> intersect(String key, Collection<String> otherKeys){
        return redisTemplate.opsForSet().intersect(key , otherKeys);
    }

    /**
     * 交集：key无序集合与otherkey无序集合的交集存储到destKey无序集合中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long intersectAndStore(String key, String otherKey, String destKey){
        return redisTemplate.opsForSet().intersectAndStore(key , otherKey , destKey);
    }

    /**
     * 交集：key对应的无序集合与多个otherKey对应的无序集合求交集存储到destKey无序集合中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey){
        return redisTemplate.opsForSet().intersectAndStore(key , otherKeys , destKey);
    }

    /**
     * 并集：key无序集合与otherKey无序集合的并集
     * @param key
     * @param otherKey
     * @return
     */
    public Set<Object> union(String key, String otherKey){
        return redisTemplate.opsForSet().union(key , otherKey);
    }

    /**
     * 并集：key无序集合与多个otherKey无序集合的并集
     * @param key
     * @param otherKeys
     * @return
     */
    public Set<Object> union(String key, Collection<String> otherKeys){
        return redisTemplate.opsForSet().union(key , otherKeys);
    }

    /**
     * 并集：key无序集合与otherkey无序集合的并集存储到destKey无序集合中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long unionAndStore(String key, String otherKey, String destKey){
        return redisTemplate.opsForSet().unionAndStore(key , otherKey , destKey);
    }

    /**
     * 并集：key无序集合与多个otherkey无序集合的并集存储到destKey无序集合中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long unionAndStore(String key, Collection<String> otherKeys, String destKey){
        return redisTemplate.opsForSet().unionAndStore(key , otherKeys , destKey);
    }

    /**
     * 差集：key无序集合与otherKey无序集合的差集
     * @param key
     * @param otherKey
     * @return
     */
    public Set<Object> difference(String key, String otherKey){
        return redisTemplate.opsForSet().difference(key , otherKey);
    }

    /**
     * 差集：key无序集合与多个otherKey无序集合的差集
     * @param key
     * @param otherKeys
     * @return
     */
    public Set<Object> difference(String key, Collection<String> otherKeys){
        return redisTemplate.opsForSet().difference(key , otherKeys);
    }

    /**
     * 差集：key无序集合与otherkey无序集合的差集存储到destKey无序集合中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long differenceAndStore(String key, String otherKey, String destKey){
        return redisTemplate.opsForSet().differenceAndStore(key , otherKey , destKey);
    }

    /**
     * 差集：key无序集合与多个otherkey无序集合的差集存储到destKey无序集合中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long differenceAndStore(String key, Collection<String> otherKeys, String destKey){
        return redisTemplate.opsForSet().differenceAndStore(key , otherKeys , destKey);
    }
}
