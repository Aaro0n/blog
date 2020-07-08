package com.github.blog.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
        @Id @GeneratedValue var id: Long? = null,
        var email: String?,
        var password: String?,
        var name: String?,
        var avatar:String?)