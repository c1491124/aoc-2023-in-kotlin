import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

//day01 part2
fun findDigit(
    lineWindowed: List<String>,
    digits: List<String>,
    minIndex: MutableList<Fragment>,
    maxIndex: MutableList<Fragment>,
    size: Int
) {
    val findFirst = lineWindowed.find { it in digits }
    if (!findFirst.isNullOrBlank()) minIndex += Fragment(lineWindowed.indexOf(findFirst), size)
    val findLast = lineWindowed.findLast { it in digits }
    if (!findLast.isNullOrBlank()) maxIndex += Fragment(lineWindowed.lastIndexOf(findLast), size)
}

fun parseInt(input: String): Int {
    return if (input.length == 1) input.toInt()
    else when (input) {
        "one" -> 1
        "two" -> 2
        "three" -> 3
        "four" -> 4
        "five" -> 5
        "six" -> 6
        "seven" -> 7
        "eight" -> 8
        "nine" -> 9
        else -> throw Exception("parse int failed")
    }
}
