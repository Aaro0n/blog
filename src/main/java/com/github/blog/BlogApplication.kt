package com.github.blog

import com.github.blog.configuration.StorageProperties
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.scheduling.annotation.EnableAsync


@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties(value = [StorageProperties::class])
class BlogApplication

fun main(args: Array<String>) {
    SpringApplication.run(BlogApplication::class.java, *args)
}