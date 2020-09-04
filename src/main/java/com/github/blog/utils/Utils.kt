package com.github.blog.utils

fun String.ipToLong(): Long {
    var ip = 0L
    this.split(".").reversed().forEachIndexed { index, s ->
        val i = s.toLong()
        ip = i shl 8 * index xor ip
    }
    return ip
}

fun Long.ipToString(): String {
    val ipAddress = StringBuilder()
    for (i in 3 downTo 0) {
        val shift = i * 8
        ipAddress.append(this and (0xff.toLong() shl shift) shr shift)
        if (i > 0) {
            ipAddress.append(".")
        }
    }
    return ipAddress.toString()
}


data class DTO(val data: String)

fun String.dto(): DTO {
    return DTO(this)
}

fun main() {
    println("255.255.255.255".ipToLong())
    println(4294967295.ipToString())
}