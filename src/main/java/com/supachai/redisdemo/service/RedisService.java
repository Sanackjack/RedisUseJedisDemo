package com.supachai.redisdemo.service;


import com.supachai.redisdemo.models.RedisRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisService {

    private final JedisConnectionFactory jedisConnectionFactory;

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisService(JedisConnectionFactory jedisConnectionFactory, RedisTemplate redisTemplate) {
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.redisTemplate = redisTemplate;
    }

    public void saveToRedis(RedisRequest request) {


//        Example Set the current key and value and set the expiration time
//
//        redisTemplate.opsForValue().set(key, value, timeout, unit)
//
//        redisTemplate.opsForValue().set("test","1234",10, TimeUnit.SECONDS);

        try {
            redisTemplate.opsForValue().set(request.getKey(), request.getValue());
        } catch (Exception e) {
            log.info("add error");
        }
    }


    public String getDataFromRedis(String key) {
        String result = "";
        try {
            result = (String) redisTemplate.opsForValue().get(key);
        } catch (Exception e) {

            log.info("get error");
        }
        return result;
    }


    public void deleteDataFromRedis(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {

            log.info("delete error");
        }

    }

}
