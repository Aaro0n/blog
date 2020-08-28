package com.github.blog

import org.apache.commons.lang3.StringUtils
import org.junit.jupiter.api.Test
import org.springframework.lang.NonNull
import org.springframework.util.Assert
import java.io.File

class FunTest {

    @Test
    fun testGetBasename() {
        println(getBasename("/") + ":test")
    }

    @Test
    fun testIp() {
        println(ipToInt("127.1.1.1"))

        println(intToIp(ipToInt("127.1.1.1")))
    }


    @Test
    fun logicalShiftRight(){
        val i = 1
        println(8 ushr i)
    }

    private fun ipToInt(ipStr: String): Int {
        val ip = ipStr.split(".")
        return (ip[0].toInt() shl 24) + (ip[1].toInt() shl 16) + (ip[2].toInt() shl 8) + ip[3].toInt()
    }


    fun intToIp(intIp: Int): String {
        val builder = StringBuilder()
        builder.append("${intIp shr 24}.")
        builder.append("${(intIp and 0x00FFFFFF) shr 16}.")
        builder.append("${(intIp and 0x0000FFFF) shr 8}.")
        builder.append("${intIp and 0x000000FF}")
        return builder.toString()
    }


    private fun getBasename(@NonNull filename: String): String? {
        var filename = filename
        Assert.hasText(filename, "Filename must not be blank")

        // Find the last slash
        val separatorLastIndex: Int = StringUtils.lastIndexOf(filename, File.separatorChar.toInt())
        if (separatorLastIndex == filename.length - 1) {
            return StringUtils.EMPTY
        }
        if (separatorLastIndex >= 0 && separatorLastIndex < filename.length - 1) {
            filename = filename.substring(separatorLastIndex + 1)
        }

        // Find last dot
        val dotLastIndex: Int = StringUtils.lastIndexOf(filename, '.'.toInt())
        return if (dotLastIndex < 0) {
            filename
        } else filename.substring(0, dotLastIndex)
    }
}