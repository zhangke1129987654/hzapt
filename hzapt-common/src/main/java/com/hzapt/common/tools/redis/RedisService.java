package com.hzapt.common.tools.redis;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * Redis服务
 *
 * @author LJ
 */
@Validated
public interface RedisService {

    /**
     * 设置缓存
     *
     * @param prefix
     * @param key
     * @param value
     * @param expire 过期时间,小于等于0不过期（秒）
     * @return
     */
    boolean set(@NotNull String prefix, @NotBlank String key, @NotNull Object value, long expire);

    /**
     * 设置缓存
     *
     * @param prefix
     * @param key
     * @param value
     * @param expire 过期时间,小于等于0不过期（秒）
     * @return
     */
    boolean setStr(@NotNull String prefix, @NotBlank String key, @NotNull String value, long expire);

    /**
     * 获取缓存字符串
     *
     * @param prefix
     * @param key
     * @return
     */
    String getStr(String prefix, String key);

    /**
     * 获取缓存Bean
     *
     * @param prefix
     * @param key
     * @param clazz
     * @return
     */
    <T> T getBean(@NotNull String prefix, @NotBlank String key, @NotNull Class<T> clazz);

    /**
     * 获取缓存集合
     *
     * @param prefix
     * @param key
     * @param clazz
     * @return
     */
    <T> List<T> getList(@NotNull String prefix, @NotBlank String key, @NotNull Class<T> clazz);

    /**
     * 判断缓存是否存在
     *
     * @param prefix
     * @param key
     * @return
     */
    boolean exists(@NotNull String prefix, @NotBlank String key);

    /**
     * 删除
     *
     * @param prefix
     * @param key
     */
    void delete(@NotNull String prefix, @NotBlank String key);

    /**
     * 批量删除
     *
     * @param redisKeys
     */
    void delete(@NotNull Set<String> redisKeys);

    /**
     * 按规则批量删除
     *
     * @param pattern
     */
    void deletePattern(@NotBlank String pattern);

    /**
     * 更新缓存失效时长
     *
     * @param prefix
     * @param key
     * @param timeout 失效时长(单位秒)
     * @return
     */
    void expire(@NotNull String prefix, @NotBlank String key, long timeout);

    /**
     * 将键的整数值增加1
     *
     * @param prefix
     * @param key
     * @return
     */
    Long incr(@NotNull String prefix, @NotBlank String key);

    /**
     * 将键的整数值减1
     *
     * @param prefix
     * @param key
     * @return
     */
    Long decr(@NotNull String prefix, @NotBlank String key);
    
    /**
              * 长度限制
     *
     * @param prefix
     * @param key
     * @param start
     * @param end
     * @return
     */
    void trim(String prefix, String key,long start,long end);
    

}
