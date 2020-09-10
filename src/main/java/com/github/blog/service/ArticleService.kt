package com.github.blog.service

import com.github.blog.dto.ArticleDto
import com.github.blog.entity.Article
import com.github.blog.entity.User
import com.github.blog.repository.ArticleRepository
import com.github.blog.utils.checkJWT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.github.blog.dto.admin.ArticleDto as AdminArticle

@Service
class ArticleService {

    @Autowired
    lateinit var articleRepository: ArticleRepository

    fun saveArticle(article: Article, user: User) {
        article.user = user
        if (article.id == null) {
            article.createTime = System.currentTimeMillis()
        } else { //update
            val savedArticle = articleRepository.findById(article.id!!).get()
            article.createTime = savedArticle.createTime
            article.categoryId = article.categoryId
        }
        article.updateTime = System.currentTimeMillis()
        article.title = article.title.replace("#", "").trim()
        articleRepository.save(article)
    }

    fun changeType(id: String, type: Int) = articleRepository.changeType(id, type)

    fun getAllArticle(): List<AdminArticle> {
        val articleList = articleRepository.findAllByOrderByUpdateTimeDesc()
        return articleList.map {
            AdminArticle(it.id!!,
                    it.title,
                    it.content,
                    it.updateTime,
                    it.type)
        }
    }


    fun getAllArticleDto(): List<ArticleDto> = articleRepository.findAllArticle()

    fun getArticleById(token: String?, id: String): Article {
        val article = articleRepository.findById(id).get()
        return if (article.type == 1) { //草稿
            try {
                checkJWT(token!!)
            } catch (e: Exception) {
                throw Exception("permission denied")
            }
            article
        } else {
            article
        }
    }

    fun deleteById(id: String) {
        articleRepository.deleteById(id)
    }
}
