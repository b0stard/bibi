package com.example.demo.repository

import com.example.demo.model.entity.User
import io.lettuce.core.dynamic.annotation.Param
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUserId(UserId: Long): User?

    @Modifying
    @Query("UPDATE User u SET u.username = :name, u.email = :email WHERE u.UserId = :id")
    fun updateUserFields(
        @Param("id") id: Long,
        @Param("name") name: String,
        @Param("email") email: String
    ): Int
    @Modifying
    @Query("DELETE FROM User u WHERE u.UserId = :id")
    fun deleteByUserId(@Param("id") id: Long): Int
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): MutableList<User>
}
