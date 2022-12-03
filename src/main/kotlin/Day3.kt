class Day3 {

    fun part1(lines: List<String>): Int {
        return lines.map { toChar(it) }.sumOf { toPriority(it) }
    }

    private fun toChar(s: String): Char {
        val a = s.substring(0, s.length / 2)
        val b = s.substring(s.length / 2)
        return a.first { it in b }
    }

    private fun toPriority(c: Char): Int {
        return if (c.isLowerCase()) c - 'a' + 1 else c - 'A' + 27
    }

    fun part2(lines: List<String>): Int {
        return lines.windowed(3, 3)
            .map {
                val a = it[0]
                val b = it[1]
                val c = it[2]
                a.toSet().intersect(b.toSet()).intersect(c.toSet()).first()
            }.sumOf { toPriority(it) }
    }

}

fun main() {
    val input = readInput("Day3")

    println("part-1 answer: ${Day3().part1(input)}")
    println("part-2 answer: ${Day3().part2(input)}")

}