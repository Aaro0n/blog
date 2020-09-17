package com.github.blog.repository

import com.github.blog.dto.ArticleDto
import com.github.blog.entity.Article
import com.github.blog.entity.User
import com.github.blog.entity.Visit
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface ArticleRepository : CrudRepository<Article, String> {

    fun findByTitle(title: String): Article?

    fun findAllByOrderByUpdateTimeDesc(): List<Article>

    @Query("select new com.github.blog.dto.ArticleDto(a.id,a.title) from Article a where a.type=0")
    fun findAllArticle(): List<ArticleDto>

    @Transactional
    @Modifying
    @Query("update Article set type=?2 where id=?1")
    fun changeType(id: String, type: Int)

    @Query("select a from Article a where a.content like %:key%")
    fun searchArticle(@Param("key") key: String): List<Article>
}

interface UserRepository : CrudRepository<User, Long> {

    fun findTopByOrderByIdDesc(): User?

    fun findByName(name: String): User?

    fun findUserById(id: Long): User?
}

interface VisitRepository : CrudRepository<Visit, Long> {

    @Query("select count (distinct a.ipAddress) from Visit a where a.articleId=?1")
    fun getVisitTimesByArticleId(articleId: String): Long
}