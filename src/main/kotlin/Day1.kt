import java.lang.Integer.max

fun main() {
    val input = readInput("Day1")

    println("part-1 answer: ${part1(input)}")
    println("part-2 answer: ${part2(input)}")
}

fun part1(lines: List<String>) : Int {
    var max = -1
    var sum = 0

    for (l in lines) {
        if (l.isEmpty()) {
            max = max(sum, max)
            sum = 0
        } else {
            sum += l.toInt()
        }
    }

    return max
}

fun part2(lines: List<String>) : Int {
    var sum = 0
    val snacks = mutableListOf<Int>()

    for (l in lines) {
        if (l.isEmpty()) {
            snacks.add(sum)
            sum = 0
        } else {
            sum += l.toInt()
        }
    }

    snacks.sortDescending()
    return snacks.take(3).sum()
}