package com.example.demo.repository

import com.example.demo.model.entity.RefreshToken
import io.lettuce.core.dynamic.annotation.Param
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken,Long> {
    fun findByToken(token: Long): RefreshToken?
    fun findByUserId(userId: Long): RefreshToken?
    fun deleteByToken(tokenId: Long)
}