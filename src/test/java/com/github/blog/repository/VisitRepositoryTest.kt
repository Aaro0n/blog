package com.github.blog.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class VisitRepositoryTest {

    @Autowired
    lateinit var visitRepository: VisitRepository

    @Test
    fun getVisitTimesByArticleId() {
        val times = visitRepository.getVisitTimesByArticleId("")
        assert(times == 1L)
    }

}