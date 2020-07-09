package com.github.blog.interceptors

import com.github.blog.utils.Result2
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun defaultErrorHandler(req: HttpServletRequest, e: Exception): Result2 {
        e.printStackTrace()
        return Result2(200, e.message ?: "error")
    }
}