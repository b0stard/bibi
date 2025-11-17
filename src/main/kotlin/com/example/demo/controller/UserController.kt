package com.example.demo.controller

import com.example.demo.model.entity.User
import com.example.demo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<User> {
        val createdUser = userService.createUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser)
    }

    @GetMapping("/search")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        val getUser = userService.findByUserId(id)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(getUser)
    }
    @PutMapping
    fun updateUser(@RequestBody user: User): ResponseEntity<User> {
        val updated = userService.updateUser(user)
        return ResponseEntity.ok(updated)
    }
    @DeleteMapping
    fun deleteUser(@RequestBody user: User): ResponseEntity<Void> {
        userService.deleteUser(user)
        return ResponseEntity.noContent().build()
    }
}