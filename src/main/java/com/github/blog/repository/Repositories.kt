package com.github.blog.repository

import com.github.blog.dto.ArticleDto
import com.github.blog.entity.Article
import com.github.blog.entity.User
import com.github.blog.entity.VisitRecord
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

interface ArticleRepository : CrudRepository<Article, Long> {
    fun findByTitle(title: String): Article?
    fun findAllByOrderByUpdateTimeDesc(): List<Article>

    @Query("select new com.github.blog.dto.ArticleDto(a.id,a.title) from Article a")
    fun findAllArticle(): List<ArticleDto>

    @Transactional
    @Modifying
    @Query("update Article set type=?2 where id=?1")
    fun changeType(id: Long, type: Int)
}

interface UserRepository : CrudRepository<User, Long> {

    fun findTopByOrderByIdDesc(): User?

    fun findByName(name: String): User?

    fun findUserById(id: Long): User?
}

interface VisitRecordRepository : CrudRepository<VisitRecord, Long> {

    @Query("select count (distinct a.ipAddress) from VisitRecord a where a.articleId=?1")
    fun getVisitTimesByArticleId(articleId: Long): Long
}