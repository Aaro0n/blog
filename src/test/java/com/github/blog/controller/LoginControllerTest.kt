package com.github.blog.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class LoginControllerTest {

    @Autowired
    lateinit var loginController: LoginController

    @Test
    fun login() {
    }

    @Test
    fun testLogin() {
        val objectNode = ObjectMapper().createObjectNode()
        objectNode.put("name", "aaron")
        objectNode.put("password", "449a36b6689d841d7d27f31b4b7cc73a")
        println(loginController.login(objectNode))
    }


}