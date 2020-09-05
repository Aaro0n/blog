package com.github.blog.entity

import javax.persistence.*

@Entity
data class Article(
        @Id
        @GeneratedValue
        var id: Long? = null,
        var createTime: Long, //创建时间
        var updateTime: Long, //更新时间
        val title: String,
        @Lob
        @Basic(fetch = FetchType.LAZY)
        @Column(name = "content", nullable = true)
        val content: String,
        @ManyToOne var user: User,
        val categoryId: Long,
        val type: Int
)