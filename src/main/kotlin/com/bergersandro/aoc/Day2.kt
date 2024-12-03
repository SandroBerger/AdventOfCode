package com.bergersandro.aoc

import com.bergersandro.utils.FileParser
import kotlin.math.absoluteValue

@Suppress("unused") // Is loaded in the main function via reflection
class Day2 : AoCDay {
    val fileParser = FileParser()
    val reports = mutableListOf<List<Int>>()
    val secondList = mutableListOf<Int>()

    init {
        loadFile()
    }

    override fun part1(): Int {
        var safeReports = 0

        reports.forEach { reportLine ->
            var isSafe = true
            val desc = reportLine[0] > reportLine[1]
            val asc = reportLine[0] < reportLine[1]

            for (i in 1 until reportLine.size) {
                val diff = reportLine[i] - reportLine[i - 1]
                if ((desc && diff !in -3..-1) || (asc && diff !in 1..3) || (!desc && !asc)) {
                    isSafe = false
                    break
                }
            }

            if (isSafe) {
                safeReports++
            }
        }

        return safeReports
    }

    override fun part2(): Int {
        var safeReports = 0

        reports.forEach { reportLine ->
            var isSafe = false

            for (j in reportLine.indices) {
                val modifiedReport = reportLine.toMutableList().apply { removeAt(j) }
                val desc = modifiedReport.zipWithNext().all { it.first > it.second }
                val asc = modifiedReport.zipWithNext().all { it.first < it.second }
                val valid = modifiedReport.zipWithNext().all { (a, b) -> (a - b).absoluteValue in 1..3 }

                if ((desc || asc) && valid) {
                    isSafe = true
                    break
                }
            }

            if (isSafe) {
                safeReports++
            }
        }

        return safeReports
    }

    private fun loadFile() {
        fileParser.parse("input-files/data-${this::class.simpleName!!.lowercase()}.txt")?.map {
            val reportsLine = it.split(" ")
            reports.add(reportsLine.map { report -> report.toInt() })
        }
    }
}