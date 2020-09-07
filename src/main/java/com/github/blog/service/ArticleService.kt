package com.github.blog.service

import com.github.blog.dto.ArticleDto
import com.github.blog.dto.admin.ArticleDto as AdminArticle
import com.github.blog.entity.Article
import com.github.blog.repository.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleService {

    @Autowired
    lateinit var articleRepository: ArticleRepository


    fun saveArticle(article: Article) {
        articleRepository.save(article)
    }

    fun getAllArticle(): List<AdminArticle> {
        val articleList = articleRepository.findAllByOrderByCreateTimeDesc()
        return articleList.map {
            AdminArticle(it.id!!,
                    it.title,
                    it.content,
                    it.updateTime,
                    it.type)
        }
    }


    fun getAllArticleDto(): List<ArticleDto> = articleRepository.findAllArticle()

    fun getArticleById(id: Long): Article = articleRepository.findById(id).get()
}
