package com.github.blog.interceptors

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component


//@Aspect
//@Component
//class LoginAspect {
//
//
//    @Pointcut("execution(* login(..))")
//    fun login() {
//    }
//
//    @Before("login()")
//    @Throws(Throwable::class)
//    fun doBefore(joinPoint: JoinPoint) {
////        val argNames: Array<String> = (joinPoint.signature as MethodSignature).parameterNames // 参数名
////        argNames.forEach { println(it) }
////        throw Exception("where am i!!!")
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "login()")
//    @Throws(Throwable::class)
//    fun doAfterReturning(ret: Any) {
//
//    }
//
//}