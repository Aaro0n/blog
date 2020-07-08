package com.github.blog.entity

import javax.persistence.*

@Entity
data class Article(
        @Id
        @GeneratedValue
        var id: Long? = null,
        val createTime: Long, //创建时间
        val updateTime: Long, //更新时间
        val title: String,
        val content: String,
        @ManyToOne var user: User,
        val categoryId: Long,
        val type:Int
)