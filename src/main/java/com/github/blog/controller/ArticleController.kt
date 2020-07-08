package com.github.blog.controller

import com.github.blog.annotation.UseAdvice
import com.github.blog.entity.Article
import com.github.blog.service.ArticleService
import com.github.blog.service.UserService
import com.github.blog.utils.getUserIdFromJWT

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@UseAdvice
@RestController
class ArticleController {

    @Autowired
    lateinit var articleService: ArticleService

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/article")
    fun createArticle(@RequestHeader("token") token: String, @RequestBody article: Article) {
        val userId = getUserIdFromJWT(token)
        article.user = userService.findUserById(userId)
        articleService.saveArticle(article)
    }

    @GetMapping("/article")
    fun getAllArticle(): List<Article> {
        return articleService.getAllArticle()
    }


    //    @GetMapping("/insert")
//    fun insertTest(): Article {
//        val user = User(email = "657307333@qq.com",
//                password = "449a36b6689d841d7d27f31b4b7cc73a",
//                name = "aaron",
//                avatar = "https://himg.bdimg.com/sys/portrait/item/pp.1.58c273de.spNUc2sVrZAMITGT4hTwnw.jpg?tt=1589852085512")
//
//        userRepository.save(user)
//
//
//        val article = Article(
//                createTime = System.currentTimeMillis(),
//                updateTime = System.currentTimeMillis(),
//                title = "这是标题",
//                content = "这是内容",
//                user = user,
//                categoryId = 1,
//                type = 0)
//        articleRepository.save(article)
//        return article
//    }
//
//    @GetMapping("/redis/insert")
//    fun insertRedis(@RequestParam name: String) {
//        tokenService.testRedis(name)
//    }

}