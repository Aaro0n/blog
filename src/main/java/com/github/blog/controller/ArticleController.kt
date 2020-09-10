package com.github.blog.controller

import com.github.blog.annotation.UseAdvice
import com.github.blog.dto.ArticleDto
import com.github.blog.entity.Article
import com.github.blog.service.ArticleService
import com.github.blog.service.UserService
import com.github.blog.service.VisitRecordService
import com.github.blog.utils.checkJWT
import com.github.blog.utils.getUserIdFromJWT
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
    lateinit var visitRecordService: VisitRecordService

    @PostMapping("/article")
    fun createArticle(@RequestHeader("token") token: String, @RequestBody article: Article) {
        val userId = getUserIdFromJWT(token)
        articleService.saveArticle(article, userService.findUserById(userId))
    }

    @PostMapping("/article/{id}")
    fun changeType(@RequestHeader("token") token: String, @PathVariable("id") id: String, @RequestParam("type") type: Int) {
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
    fun getArticle(@PathVariable("id") id: String, request: HttpServletRequest): Article {
        val article = articleService.getArticleById(id)
        visitRecordService.save(article.id!!, request.remoteAddr)
        return article
    }

    @DeleteMapping("/article/{id}")
    fun deleteArticle(@RequestHeader("token") token: String, @PathVariable("id") id: String) {
        checkJWT(token)
        articleService.deleteById(id)
    }
}