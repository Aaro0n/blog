package com.github.blog.service

import com.github.blog.entity.VisitRecord
import com.github.blog.repository.VisitRecordRepository
import com.github.blog.utils.ipToLong
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class VisitRecordService {

    @Autowired
    lateinit var visitRecordRepository: VisitRecordRepository

    fun save(visitRecord: VisitRecord) {
        visitRecordRepository.save(visitRecord)
    }

    fun save(articleId: Long, remoteAddr: String) {
        val visitRecord = VisitRecord(null, System.currentTimeMillis(), remoteAddr.ipToLong(), articleId)
        visitRecordRepository.save(visitRecord)
    }
}