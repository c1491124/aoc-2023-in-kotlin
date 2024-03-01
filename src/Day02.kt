fun main() {
    fun checkCondition(num: String, color: String): Boolean {
        return if (num.toInt() > when (color) {
                "red" -> 12
                "green" -> 13
                "blue" -> 14
                else -> throw Exception("fun chenCondition failed")
            }
        ) true else false
    }

    fun part1(input: List<String>): Int {
        var previousLine = ""
        return input.sumOf {
            //Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            //split "game n" and others , save n as num
                line ->
            if (line[0] == 'G') previousLine = line else previousLine += line
            var index = 0
            previousLine.split(":").also { index = it[0].split(" ")[1].toInt() }[1].split(";")
                //split each component
                .forEach { draw ->
                    draw.split(",")
                        //split color
                        .forEach {
                            val (num, color) = it.trim().split(" ")
                            if (checkCondition(num, color)) {
                                index = 0
                            }
                        }
                }
            index
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            var redN = 1
            var greenN = 1
            var blueN = 1
            line.split(":")[1].split(";").forEach { draw ->
                draw.split(",")
                    .forEach {
                        val (num, color) = it.trim().split(" ")
                        when {
                            color == "red" && num.toInt() > redN -> redN = num.toInt()
                            color == "green" && num.toInt() > greenN -> greenN = num.toInt()
                            color == "blue" && num.toInt() > blueN -> blueN = num.toInt()
                        }
                    }
            }
            redN * greenN * blueN
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    //check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(testInput).println()
    part2(testInput).println()
}