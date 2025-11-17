package  com.example.demo.controller

import com.example.demo.model.entity.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(@RequestBody user: User): ResponseEntity<Map<String, String>> {
val login = authService.login(user)
        return ResponseEntity.ok(login)
    }
}