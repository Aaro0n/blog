package com.github.blog.exception

class StorageFileNotFoundException : StorageException {
    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}