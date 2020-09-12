package com.github.blog.interceptors

import com.github.blog.utils.Result2
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import java.lang.reflect.UndeclaredThrowableException
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun defaultErrorHandler(req: HttpServletRequest, e: Exception): Result2 {
        e.printStackTrace()
        if (e is UndeclaredThrowableException) {
            e.undeclaredThrowable.message
        }
        return when {
            e.message != null -> {
                Result2(200, e.message!!)
            }
            e is UndeclaredThrowableException -> {
                Result2(200, e.undeclaredThrowable.message ?: "error")
            }
            else -> {
                Result2(200, "error")
            }
        }
    }
}