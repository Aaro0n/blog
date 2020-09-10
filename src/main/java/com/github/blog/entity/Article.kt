package com.github.blog.entity

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Article(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "com.github.blog.utils.UUIDGenerator")
        var id: String? = null,

        var createTime: Long, //创建时间

        var updateTime: Long, //更新时间

        var title: String,

        @Lob
        @Basic(fetch = FetchType.LAZY)
        @Column(name = "content", nullable = true)
        val content: String,

        @ManyToOne
        var user: User,

        var categoryId: Long,
//        type 0发布 1草稿
        val type: Int
)