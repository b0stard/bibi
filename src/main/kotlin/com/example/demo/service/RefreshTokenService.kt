package com.example.demo.service

import com.example.demo.model.entity.RefreshToken
import com.example.demo.repository.RefreshTokenRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RefreshTokenService(
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun findByToken(token: Long): RefreshToken? {
        if (refreshTokenRepository.existsById(token)) {
            throw IllegalArgumentException("token не существует")
        }
        return refreshTokenRepository.findByIdOrNull(token)
    }

    fun findByUserId(userId: Long): RefreshToken? {
        if (refreshTokenRepository.findByIdOrNull(userId) != null) {
            throw IllegalArgumentException("User с id $userId не имеет такой токен")
        }
        return refreshTokenRepository.findByIdOrNull(userId)
    }

    fun saveToken(userId: Long, token: Long): RefreshToken {
        val refreshToken = RefreshToken(token = token, id = userId)
        return refreshTokenRepository.save(refreshToken)
    }

    fun deleteByToken(tokenId: Long) {
        refreshTokenRepository.deleteById(tokenId)
    }
}