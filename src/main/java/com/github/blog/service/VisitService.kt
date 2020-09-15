package com.github.blog.service

import com.github.blog.entity.Visit
import com.github.blog.repository.VisitRepository
import com.github.blog.utils.ipToLong
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class VisitService {

    @Autowired
    lateinit var visitRepository: VisitRepository

    fun save(visit: Visit) {
        visitRepository.save(visit)
    }

    fun save(articleId: String, remoteAddr: String) {
        val visit = Visit(null, System.currentTimeMillis(), remoteAddr.ipToLong(), articleId)
        visitRepository.save(visit)
    }
}