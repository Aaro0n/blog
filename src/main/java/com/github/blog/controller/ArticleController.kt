package com.github.blog.controller

import com.github.blog.annotation.UseAdvice
import com.github.blog.dto.ArticleDto
import com.github.blog.entity.Article
import com.github.blog.entity.VisitRecord
import com.github.blog.repository.VisitRecordRepository
import com.github.blog.service.ArticleService
import com.github.blog.service.UserService
import com.github.blog.utils.checkJWT
import com.github.blog.utils.getUserIdFromJWT
import com.github.blog.utils.ipToLong
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import com.github.blog.dto.admin.ArticleDto as AdminArticle


@UseAdvice
@RestController
@CrossOrigin
class ArticleController {

    @Autowired
    lateinit var articleService: ArticleService

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var visitRecordRepository: VisitRecordRepository

    @PostMapping("/article")
    fun createArticle(@RequestHeader("token") token: String, @RequestBody article: Article) {
        val userId = getUserIdFromJWT(token)
        article.user = userService.findUserById(userId)
        if (article.id == null) {
            article.updateTime = System.currentTimeMillis()
        }
        article.updateTime = System.currentTimeMillis()
        article.title = article.title.replace("#", "").trim()
        articleService.saveArticle(article)
    }

    @PostMapping("/article/{id}")
    fun changeType(@RequestHeader("token") token: String, @PathVariable("id") id: Long, @RequestParam("type")type: Int) {
        checkJWT(token)
        articleService.changeType(id, type)
    }

    @GetMapping("/article")
    fun getAllArticle(): List<AdminArticle> {
        return articleService.getAllArticle()
    }

    @GetMapping("/articles")
    fun getArticleList(): List<ArticleDto> {
        return articleService.getAllArticleDto()
    }

    @GetMapping("/article/{id}")
    fun getArticle(@PathVariable("id") id: Long, request: HttpServletRequest): Article {
        val article = articleService.getArticleById(id)
        val remoteAddr = request.remoteAddr
        println(remoteAddr)
        val visitRecord = VisitRecord(null, System.currentTimeMillis(), remoteAddr.ipToLong(), article.id!!)
        visitRecordRepository.save(visitRecord)
        return article
    }
}