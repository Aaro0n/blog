package com.github.blog.controller

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ArticleControllerTest {

    @Autowired
    lateinit var articleController: ArticleController

    @Test
    fun getArticleList() {
        articleController.getArticleList().forEach{
            println("#id=${it.id}/title=${it.title}")
        }
    }
}