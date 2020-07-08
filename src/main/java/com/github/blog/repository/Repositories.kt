package com.github.blog.repository

import com.github.blog.entity.Article
import com.github.blog.entity.User
import org.springframework.data.repository.CrudRepository

interface ArticleRepository : CrudRepository<Article, Long> {
    fun findByTitle(title: String): Article?
    fun findAllByOrderByCreateTimeDesc(): List<Article>
}

interface UserRepository : CrudRepository<User, Long> {

    fun findTopByOrderByIdDesc(): User?

    fun findByName(name: String): User?

    fun findUserById(id: Long): User?
}