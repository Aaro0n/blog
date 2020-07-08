package com.github.blog.utils

data class Result3(val code: Int = 0, val message: String = "success", val data: Any?)
data class Result2(val code: Int, val message: String)

val SUCCESS = Result2(0, "success")