package com.github.blog.service

import com.fasterxml.jackson.databind.JsonNode
import com.github.blog.entity.User
import com.github.blog.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun verify(node: JsonNode): User {
        val name = node["name"].asText()
        val password = node["password"].asText()
        val user = userRepository.findByName(name) ?: throw Exception("没有该账户")
        return if (user.password == password) {
            user
        } else {
            throw Exception("密码错误")
        }
    }

    fun findOneUser(): User {
        return userRepository.findTopByOrderByIdDesc()!!
    }

    fun findUserById(userId: Long): User {
        return userRepository.findUserById(userId) ?: throw Exception("user not exist")
    }
}