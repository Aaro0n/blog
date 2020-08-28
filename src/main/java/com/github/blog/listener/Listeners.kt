package com.github.blog.listener

import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

open class Base

class A : Base()

class B : Base()


@Component
class EventListenerA {


    @Async
    @EventListener
    fun eventA(a: A) {
        println("eventA" + Thread.currentThread().name)
    }

}


@Component
class EventListenerB {

    @Async
    @EventListener
    fun eventB(b: B) {
        println("eventB" + Thread.currentThread().name)
    }

}

@Component
class EventListenerParent {

    @Async
    @EventListener
    fun eventBase(base: Base) {
        println("eventBase" + Thread.currentThread().name)
    }

}