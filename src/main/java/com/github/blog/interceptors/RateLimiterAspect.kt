package com.github.blog.interceptors

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import com.google.common.util.concurrent.RateLimiter
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.concurrent.TimeUnit


@Aspect
@Component
class RateLimiterAspect {

    private val rateLimit: LoadingCache<String, RateLimiter> = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .build<String, RateLimiter>(object : CacheLoader<String, RateLimiter>() {
                override fun load(key: String): RateLimiter {
                    return RateLimiter.create(2.0)
                }
            })

    @Pointcut("@annotation(com.github.blog.interceptors.RateLimit)")
    fun rateLimiterAspect() {
    }

    @Before("rateLimiterAspect()")
    fun deBefore(joinPoint: JoinPoint) {
        val ip = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.remoteAddr
        val limiter = rateLimit.get(ip)
        if (!limiter.tryAcquire()) throw Exception("visiting too frequently")
    }
}


annotation class RateLimit