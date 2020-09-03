package com.github.blog.configuration

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties("storage")
class StorageProperties {

    var location = "upload-dir"

}