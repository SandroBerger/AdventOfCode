package com.bergersandro.aoc

import com.bergersandro.utils.FileParser

@Suppress("unused") // Is loaded in the main function via reflection
class Day3 : AoCDay {
    val fileParser = FileParser()
    val instructions = mutableListOf<String>()

    init {
        loadFile()
    }

    override fun part1(): Int {
        return instructions.flatMap { extractMulExpressions(it) }.mapNotNull { multiplyMulExpression(it) }.sum()
    }

    override fun part2(): Int {
        val filteredMultiplications =
            instructions.joinToString().split(Regex("""(?=do\(\)|don't\(\))""")).filterNot { it.contains("don't()") }
        return filteredMultiplications.flatMap { extractMulExpressions(it) }.mapNotNull { multiplyMulExpression(it) }
            .sum()
    }

    private fun loadFile() {
        fileParser.parse("data-${this::class.simpleName!!.lowercase()}.txt")?.map {
            instructions.add(it)
        }
    }

    private fun extractMulExpressions(input: String): List<String> {
        val regex = """mul\(\d+,\d+\)""".toRegex()
        return regex.findAll(input).map { it.value }.toList()
    }

    private fun multiplyMulExpression(input: String): Int? {
        val regex = """mul\((\d+),(\d+)\)""".toRegex()
        val matchResult = regex.find(input)
        return matchResult?.let {
            val (x, y) = it.destructured
            x.toInt() * y.toInt()
        }
    }
}