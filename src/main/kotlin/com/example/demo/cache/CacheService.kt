package com.example.demo.cache

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import tools.jackson.databind.ObjectMapper
import java.util.concurrent.TimeUnit


@Service
class CacheService(
    private val redisTemplate: StringRedisTemplate,
    private val objectMapper: ObjectMapper
) {
    fun put(key: String, value: Any, ttlSeconds: Long) {
        val json = objectMapper.writeValueAsString(value)
        redisTemplate.opsForValue().set(key, json, ttlSeconds, TimeUnit.SECONDS)
    }
    // получить объект по ключу
    fun <T> get(key: String, clazz: Class<T>): T? {
        val json = redisTemplate.opsForValue().get(key)
        return objectMapper.readValue(json, clazz)
    }
    fun delete(key: String) {
        redisTemplate.delete(key)
    }
    fun exists(key: String): Boolean {
        return redisTemplate.hasKey(key) == true
    }
    fun saveRefreshToken(userId: Long, token: String, ttlSeconds: Long = 7 * 24 * 3600) {
        put("refresh:$userId", token, ttlSeconds)
    }
}