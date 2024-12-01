package com.bergersandro.utils

class FileParser {
    fun parse(filename: String): List<String>? {
        val file = this::class.java.getResource("/$filename")?.readText()
        return file?.split("\n")
            ?.map { it.trim() }
            ?.filter { it.isNotBlank() }
    }
}