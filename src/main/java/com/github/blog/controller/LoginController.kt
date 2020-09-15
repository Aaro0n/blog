package com.github.blog.controller

import com.github.blog.annotation.UseAdvice
import com.github.blog.dto.LoginDto
import com.github.blog.service.UserService
import com.github.blog.utils.createJWT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@UseAdvice
@RestController
@CrossOrigin
class LoginController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/login")
    fun login(@RequestParam("name") name: String, @RequestParam("password") password: String): LoginDto {
        val user = userService.verify(name, password)
        return LoginDto(createJWT(user))
    }

    @PostMapping("/register")
    fun register(@RequestParam("name") name: String, @RequestParam("password") password: String) {
        userService.register(name, password)
    }
}