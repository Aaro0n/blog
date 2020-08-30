package com.github.blog.service

import com.github.blog.entity.VisitRecord
import com.github.blog.repository.VisitRecordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class VisitRecordService {

    @Autowired
    lateinit var visitRecordRepository: VisitRecordRepository

    fun insert(visitRecord: VisitRecord) {
        visitRecordRepository.save(visitRecord)
    }
}