package com.github.blog.controller

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
        println(loginController.login("aaron", "449a36b6689d841d7d27f31b4b7cc73a"))
    }


}