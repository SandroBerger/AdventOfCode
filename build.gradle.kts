import de.undercouch.gradle.tasks.download.Download

plugins {
    kotlin("jvm") version "2.0.20"
    id("de.undercouch.download") version "5.0.0"
}

group = "com.berger-sandro"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.reflections:reflections:0.10.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<JavaExec>("run") {
    group = "application"
    description = "Run the main class"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.bergersandro.AoC2024MainKt")
}

fun registerDownloadTask(day: Int, sessionCookie: String) {
    tasks.register<Download>("downloadInputDay$day") {
        description = "Download input file for Advent of Code Day $day"
        val destFile = file("src/main/resources/input-files/data-day$day.txt")
        onlyIf { !destFile.exists() }
        dest(destFile)
        onlyIfModified(true)
        header("Cookie", "session=$sessionCookie")
        src("https://adventofcode.com/2024/day/$day/input")
        println("Downloaded input file for Day $day")
    }
}

val adventOfCodeSessionCookie: String by project

(1..24).forEach { day ->
    registerDownloadTask(day, adventOfCodeSessionCookie)
}
