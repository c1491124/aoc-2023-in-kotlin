data class Fragment(val index: Int, val length: Int)

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            line.find { it.isDigit() }!!.toString().toInt() * 10
        } + input.sumOf { line ->
            line.reversed().find { it.isDigit() }!!.toString().toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val digits = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val minIndex = mutableListOf<Fragment>()
        val maxIndex = mutableListOf<Fragment>()
        var sum = 0
        input.forEach { line ->
            // parse 1,2,6
            val lineWin3 = line.windowed(3)
            findDigit(lineWin3, digits, minIndex, maxIndex, 3)
            // parse 4,5,9
            val lineWin4 = line.windowed(4)
            findDigit(lineWin4, digits, minIndex, maxIndex, 4)
            // parse 3,7,8
            val lineWin5 = line.windowed(5)
            findDigit(lineWin5, digits, minIndex, maxIndex, 5)
            // parse number
            val lineWin1 = line.windowed(1)
            findDigit(lineWin1, (1..9).toList().map { it.toString() }, minIndex, maxIndex, 1)
            //add to sum
            val fragmentMin =
                line.substring(minIndex.minOf { it.index }..<minIndex.minOf { it.index } + minIndex.minBy { it.index }.length)
            val fragmentMax =
                line.substring(maxIndex.maxOf { it.index }..<maxIndex.maxOf { it.index } + maxIndex.maxBy { it.index }.length)
            sum += (parseInt(fragmentMin) * 10 + parseInt(fragmentMax))
            //test
            println(parseInt(fragmentMin) * 10 + parseInt(fragmentMax))
            //clear
            minIndex.clear()
            maxIndex.clear()
            println(sum)
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    //check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
