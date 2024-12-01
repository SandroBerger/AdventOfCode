# Advent of Code 2024
This repository contains my solutions for the Advent of Code 2024 challenge.

## Usage
To see the solutions run the following command:
```bash
./gradlew run
```

When running the build command, for convenience the required input files will be downloaded.

**Attention: To use the automatic download of input files, you need to provide your session cookie in the `gradle.properties` file. Never share or commit your session cookie with anyone!**

Example `gradle.properties` file:
```properties
adventOfCodeSessionCookie=your_session_cookie
```
Afterward you can run the task `downloadInputDay01` to download the input file for day 1.

## Puzzles
* [Day 1](https://adventofcode.com/2024/day/1)

## Solutions
* [Day 1](src/main/kotlin/com/bergersandro/aoc/Day1.kt)