package com.github.blog.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    lateinit var articleRepository: ArticleRepository

    @Test
    fun searchArticle() {
        val searchArticle = articleRepository.searchArticle("测试")
        assert(searchArticle.isNotEmpty() && searchArticle[0].id == "847fe24a")
    }
}