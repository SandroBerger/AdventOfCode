package com.bergersandro.aoc

import com.bergersandro.utils.FileParser

@Suppress("unused") // Is loaded in the main function via reflection
class Day1 : AoCDay {
    val fileParser = FileParser()
    val firstList = mutableListOf<Int>()
    val secondList = mutableListOf<Int>()

    init {
        loadFile()
        firstList.sort()
        secondList.sort()
    }

    override fun part1(): Int {
        var totalDistance = 0

        for (i in 0 until firstList.size) {
            if (firstList[i] > secondList[i]) {
                totalDistance += firstList[i] - secondList[i]
                continue
            }
            totalDistance += secondList[i] - firstList[i]
        }

        return totalDistance
    }

    override fun part2(): Int {
        val multipliers = secondList.groupBy { it }.mapValues { it.value.size }
        return firstList.map {
            it * (multipliers[it] ?: 0)
        }.sum()
    }

    private fun loadFile() {
        fileParser.parse("data-${this::class.simpleName!!.lowercase()}.txt")?.map {
            val pairs = it.split("   ")
            firstList.add(pairs[0].toInt())
            secondList.add(pairs[1].toInt())
        }
    }
}