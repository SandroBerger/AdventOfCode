package com.bergersandro

import com.bergersandro.aoc.AoCDay
import org.reflections.Reflections
import kotlin.reflect.full.createInstance
import kotlin.system.measureTimeMillis

fun main() {
    val days = Reflections(AoCDay::class.java.`package`.name)
        .getSubTypesOf(AoCDay::class.java)
        .mapNotNull { it.kotlin.createInstance() }
        .sortedBy { it::class.simpleName }

    days.forEach {
        val executionTime = measureTimeMillis {
            println("------------ ${it.javaClass.simpleName} ------------")
            println("--> Solution part 1: ${it.part1()}")
            println("--> Solution part 2: ${it.part2()}")
        }
        println("Execution time: $executionTime ms")
    }
}