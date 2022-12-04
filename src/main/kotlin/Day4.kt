class Day4 {

    fun part1(lines: List<String>): Int {
        return lines.map { it.split(",") }
            .count {
                val a = it[0].split("-")
                val b = it[1].split("-")
                val r1 = a[0].toInt()..a[1].toInt()
                val r2 = b[0].toInt()..b[1].toInt()
                (r1.first in r2 && r1.last in r2) || (r2.first in r1 && r2.last in r1)
            }
    }

    fun part2(lines: List<String>): Int {
        return lines.map { it.split(",") }
            .count {
                val a = it[0].split("-")
                val b = it[1].split("-")
                val r1 = a[0].toInt()..a[1].toInt()
                val r2 = b[0].toInt()..b[1].toInt()
                (r1.first in r2 || r1.last in r2) || (r2.first in r1 || r2.last in r1)
            }
    }
}

fun main() {

    val test = listOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
        "2-8,3-7",
        "6-6,4-6",
        "2-6,4-8"
    )
    val input = readInput("Day4")

    println("part-1 answer: ${Day4().part1(input)}")
    println("part-2 answer: ${Day4().part2(input)}")

}