package com.github.blog.interceptors

import com.github.blog.annotation.UseAdvice
import com.github.blog.utils.Result2
import com.github.blog.utils.Result3
import com.github.blog.utils.SUCCESS
import org.springframework.context.annotation.Configuration
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice


@Configuration
class GlobalReturnConfig {

    @RestControllerAdvice
    internal class ResultResponseAdvice : ResponseBodyAdvice<Any> {
        override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
            returnType.containingClass.annotations.forEach {
                if (it.annotationClass == UseAdvice::class) return true
            }
            returnType.method?.annotations?.forEach {
                if (it.annotationClass == UseAdvice::class) return true
            }
            return false
        }

        override fun beforeBodyWrite(body: Any?,
                                     returnType: MethodParameter,
                                     selectedContentType: MediaType,
                                     selectedConverterType: Class<out HttpMessageConverter<*>>,
                                     request: ServerHttpRequest,
                                     response: ServerHttpResponse): Any {
            if (body is Result2) return body
            if (body != null) return Result3(data = body)
            return SUCCESS
        }
    }
}