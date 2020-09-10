package com.github.blog.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class VisitRecordRepositoryTest {

    @Autowired
    lateinit var visitRecordRepository: VisitRecordRepository

    @Test
    fun getVisitTimesByArticleId() {
        val times = visitRecordRepository.getVisitTimesByArticleId("")
        assert(times == 1L)
    }

}