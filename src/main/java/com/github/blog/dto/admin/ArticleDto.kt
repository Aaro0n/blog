package com.github.blog.dto.admin


data class ArticleDto(val id: String,
                         val title: String,
                         val content: String,
                         val updateTime: Long,
                         val type: Int)