package com.github.blog.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
data class Visit(
        @Id
        @GeneratedValue
        var id: Long? = null,
        var visitTime: Long,
        var ipAddress: Long,
        var articleId: String
)