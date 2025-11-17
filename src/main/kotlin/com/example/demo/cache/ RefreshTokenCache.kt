package com.example.demo.cache

class RefreshTokenCache(
    private val ttlMillis: Long = 1000L * 60L * 60L * 24L //24часа
) {

     data class Entry(
        val token: String,
        val expiresAt: Long
    )
    private val cache = mutableMapOf<String, Entry>() // userId -> Entry

    fun save(userId: String, refreshToken: String) {
        val entry =  Entry(token = refreshToken, expiresAt = System.currentTimeMillis())
        cache[userId] = entry
    }
    fun get(userId: String): String? {
        val entry = cache[userId] ?: return null
        return if (entry.expiresAt > System.currentTimeMillis()) {
            entry.token
        } else {
            cache.remove(userId)
            null
        }
    }
    fun invalidate(userId: String) {
        cache.remove(userId)
    }
    fun invalidateToken(refreshToken: String) {
        val key = cache.entries
            .firstOrNull { it.value.token == refreshToken }
            ?.key
        if (key != null) cache.remove(key)
    }
}