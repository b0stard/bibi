package com.example.demo.service

import com.example.demo.model.entity.User
import com.example.demo.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {
    fun register(username: String, password: String): String {
        if (userRepository.existsByUsername(username)) {
            throw IllegalArgumentException("Пользователь уже существует")
        }
        val user = User(
            username = username,
            password = passwordEncoder.encode(password)
        )
        userRepository.save(user)
        return jwtService.generateToken(user)
    }
    fun login(username: String, password: String): String {
        val authToken = UsernamePasswordAuthenticationToken(username, password)
        authenticationManager.authenticate(authToken)
        val user = userRepository.findByUsername(username)
            ?: throw IllegalArgumentException("Пользователь не найден")
        return jwtService.generateToken(user)
    }
}
