package com.example.demo.service

import com.example.demo.model.entity.User
import com.example.demo.repository.UserRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.repository.findByIdOrNull
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun findByUserId(UserId: Long): User? {
        return userRepository.findByIdOrNull(UserId)
    }
@Transactional
    fun createUser(user: User): User {
        return try {
            userRepository.save(user)
        } catch (ex: DataIntegrityViolationException) {
            throw IllegalArgumentException("User с id ${user.UserId} уже существует")
        }
    }

    @Transactional
    fun updateUser(user: User): User {
        val updatedUser = userRepository.updateUserFields(user.UserId, user.username, user.email)
        if (updatedUser == 0) {
            throw IllegalArgumentException("User с id ${user.UserId} не найден")
        }
        return user
    }

    @Transactional
    fun deleteUser(user: User): User {
        val deleteUser = userRepository.deleteByUserId(user.UserId)
        if (deleteUser == 0) {
            throw IllegalArgumentException("User с id ${user.UserId} не найден")
        }
        return user
    }
}