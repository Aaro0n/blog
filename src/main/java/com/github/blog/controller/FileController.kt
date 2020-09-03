package com.github.blog.controller

import com.github.blog.annotation.UseAdvice
import com.github.blog.service.StorageService
import com.github.blog.service.UserService
import com.github.blog.utils.DTO
import com.github.blog.utils.getUserIdFromJWT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
class FileController {

    @Autowired
    lateinit var storageService: StorageService

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/upload")
    @UseAdvice
    fun upload(@RequestHeader("token") token: String, @RequestParam("file") file: MultipartFile): DTO {
        val userId = getUserIdFromJWT(token)
        userService.findUserById(userId)
        storageService.store(file)
        return "/file/${file.originalFilename}".DTO()
    }

    @GetMapping("/file/{filename}", produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    fun file(@PathVariable filename: String): Resource {
        return storageService.loadAsResource(filename)
    }

    @GetMapping("/favicon.ico", produces = [MediaType.IMAGE_JPEG_VALUE])
    fun favicon(): Resource {
        return storageService.loadAsResource("favicon.ico")
    }

}