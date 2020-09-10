package com.github.blog.dto

import com.github.blog.entity.Article

data class ArticleDto @JvmOverloads constructor(val id: String, val title: String, val content: String? = null)
//data class ArticleDto(val id: String, val title: String, val content: String? = null) {
//    constructor(id: String, title: String) : this(id, title, null)
//}

fun Article.toDtoWithContent(): ArticleDto {
    return ArticleDto(this.id!!, this.title, this.content)
}