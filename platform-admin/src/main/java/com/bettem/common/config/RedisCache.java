package com.bettem.common.config;

import com.bettem.common.utils.ApplicationContextHolder;
import com.bettem.common.utils.Constant;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisCache implements Cache {
    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id; // cache instance id
    private RedisTemplate redisTemplate;

    private static final long EXPIRE_TIME_IN_MINUTES = 24; // redis过期时间

    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Put query result to redis
     *
     * @param key
     * @param value
     */
    @Override
    @SuppressWarnings("unchecked")
    public void putObject(Object key, Object value) {
        try {
            if(Constant.REDIS_OPEN){
                logger.debug("redis缓存key："+key.toString()+"！！");
                RedisTemplate redisTemplate = getRedisTemplate();
                ValueOperations<Object, Object> opsForValue=redisTemplate.opsForValue();
                opsForValue.set(key.toString(), value, EXPIRE_TIME_IN_MINUTES, TimeUnit.HOURS);
                logger.debug("将结果集PUT到Redis！！");
            }else{
                logger.debug("Redis服务未开启，直接操作数据库");
            }
        }
        catch (Throwable t) {
            logger.error("将结果集PUT到Redis，发生异常！！", t);
        }
    }

    /**
     * Get cached query result from redis
     *
     * @param key
     * @return
     */
    @Override
    public Object getObject(Object key) {
        try {

            if(Constant.REDIS_OPEN){
                logger.debug("redis缓存key："+key.toString()+"！！");
                RedisTemplate redisTemplate = getRedisTemplate();
                ValueOperations<Object, Object> opsForValue=redisTemplate.opsForValue();
                logger.debug("从Redis获取结果集！！");
                return opsForValue.get(key.toString());
            }else{
                logger.debug("Redis服务未开启，直接查询数据库");
                return null;
            }
        }
        catch (Throwable t) {
            logger.error("从Redis获取结果集，发生异常！！", t);
            return null;
        }
    }

    /**
     * Remove cached query result from redis
     *
     * @param key
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object removeObject(Object key) {
        try {
            if(Constant.REDIS_OPEN){
                RedisTemplate redisTemplate = getRedisTemplate();
                redisTemplate.delete(key.toString());
                logger.debug("redis缓存key："+key.toString()+"！！");
                logger.debug("根据key将结果集从Redis中移除！！");
            }else{
                logger.debug("Redis服务未开启，不移除缓存");
            }
        }
        catch (Throwable t) {
            logger.error("根据key将结果集从Redis中移除，发生异常！！", t);
        }
        return null;
    }

    /**
     * Clears this cache instance
     */
    @Override
    public void clear() {
        if(Constant.REDIS_OPEN){
            RedisTemplate redisTemplate = getRedisTemplate();
            redisTemplate.execute((RedisCallback) connection -> {
                connection.flushDb();
                return null;
            });
            logger.debug("清除Redis中所有结果集！！");
        }else{
            logger.debug("Redis服务未开启，不清空缓存");
        }
    }

    /**
     * This method is not used
     *
     * @return
     */
    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    private RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
        }
        return redisTemplate;
    }
}
