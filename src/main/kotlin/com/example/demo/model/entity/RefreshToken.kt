package com.example.demo.model.entity

import org.springframework.data.redis.core.RedisHash
import java.time.Duration

@RedisHash("refresh_tokens")
data class RefreshToken(
    val id: Long = 0,
    val token: Long,
    val ttl: Long = Duration.ofDays(7).seconds

)