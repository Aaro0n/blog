package com.github.blog.controller

import com.fasterxml.jackson.databind.JsonNode
import com.github.blog.annotation.UseAdvice
import com.github.blog.dto.LoginDto
import com.github.blog.listener.A
import com.github.blog.listener.B
import com.github.blog.listener.Base
import com.github.blog.service.UserService
import com.github.blog.utils.createJWT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@UseAdvice
@RestController
@CrossOrigin
class LoginController {

    @Autowired
    lateinit var publisher: ApplicationEventPublisher

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/login")
    fun login(@RequestBody node: JsonNode): LoginDto {
        val name = node["name"].asText()
        val password = node["password"].asText()
        val user = userService.verify(name, password)
        return LoginDto(createJWT(user))
    }

    @PostMapping("/testlogin")
    fun login() {
        println("testlogin")
        publisher.publishEvent(A())
        publisher.publishEvent(B())
        publisher.publishEvent(Base())
    }
}