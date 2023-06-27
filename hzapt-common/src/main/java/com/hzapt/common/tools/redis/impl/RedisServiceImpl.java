package com.hzapt.common.tools.redis.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.hzapt.common.tools.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean set(String prefix, String key, Object value, long expire) {
        return setStr(prefix, key, JSONUtil.toJsonStr(value), expire);
    }

    @Override
    public boolean setStr(String prefix, String key, String value, long expire) {
        String redisKey = StrUtil.builder(prefix, ":", key).toString();
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(redisKey, value);
        if (expire > 0) {
            return redisTemplate.expire(redisKey, expire, TimeUnit.SECONDS);
        }
        return true;
    }

    @Override
    public boolean exists(String prefix, String key) {
        String redisKey = StrUtil.builder(prefix, ":", key).toString();
        return redisTemplate.hasKey(redisKey);
    }

    @Override
    public void delete(String prefix, String key) {
        String redisKey = StrUtil.builder(prefix, ":", key).toString();
        if (redisTemplate.hasKey(redisKey)) {
            redisTemplate.delete(redisKey);
        }
    }

    @Override
    public void delete(Set<String> keys) {
        if (CollUtil.isNotEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    @Override
    public void deletePattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (CollUtil.isNotEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    @Override
    public String getStr(String prefix, String key) {
        String redisKey = StrUtil.builder(prefix, ":", key).toString();
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.get(redisKey);
    }

    @Override
    public <T> T getBean(String prefix, String key, Class<T> clazz) {
        String value = this.getStr(prefix, key);
        return StrUtil.isBlank(value) ? null : JSONUtil.toBean(value, clazz);
    }

    @Override
    public <T> List<T> getList(String prefix, String key, Class<T> clazz) {
        String value = this.getStr(prefix, key);
        return StrUtil.isBlank(value) ? null : JSONUtil.toList(new JSONArray(value), clazz);
    }

    @Async
    @Override
    public void expire(String prefix, String key, long timeout) {
        String redisKey = StrUtil.builder(prefix, ":", key).toString();
        redisTemplate.expire(redisKey, timeout, TimeUnit.SECONDS);
    }

    @Override
    public Long incr(String prefix, String key) {
        String redisKey = StrUtil.builder(prefix, ":", key).toString();
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.increment(redisKey);
    }

    @Override
    public Long decr(String prefix, String key) {
        String redisKey = StrUtil.builder(prefix, ":", key).toString();
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.decrement(redisKey);
    }
    
    @Override
    public void trim(String prefix, String key,long start,long end) {
    	String redisKey = StrUtil.builder(prefix, ":", key).toString();
    	redisTemplate.opsForList().trim(redisKey, start, end);
    }
    

}
