class Day2 {

    fun part1(lines: List<String>): Int {
        var sum = 0
        for (line in lines) {
            val a = line.split(" ")[0]
            val b = line.split(" ")[1]
            var score = 0
            when (a) {
                "A" -> {
                    when (b) {
                        "X" -> score += (1 + 3)
                        "Y" -> score += (2 + 6)
                        "Z" -> score += (3 + 0)
                    }
                }
                "B" -> {
                    when (b) {
                        "X" -> score += (1 + 0)
                        "Y" -> score += (2 + 3)
                        "Z" -> score += (3 + 6)
                    }
                }
                "C" -> {
                    when (b) {
                        "X" -> score += (1 + 6)
                        "Y" -> score += (2 + 0)
                        "Z" -> score += (3 + 3)
                    }
                }
            }
            sum += score
        }
        return sum
    }

    fun part2(lines: List<String>): Int {
        var sum = 0
        for (line in lines) {
            val a = line.split(" ")[0]
            val b = line.split(" ")[1]
            var score = 0
            when (a) {
                "A" -> {
                    when (b) {
                        "X" -> score += (3 + 0)
                        "Y" -> score += (1 + 3)
                        "Z" -> score += (2 + 6)
                    }
                }
                "B" -> {
                    when (b) {
                        "X" -> score += (1 + 0)
                        "Y" -> score += (2 + 3)
                        "Z" -> score += (3 + 6)
                    }
                }
                "C" -> {
                    when (b) {
                        "X" -> score += (2 + 0)
                        "Y" -> score += (3 + 3)
                        "Z" -> score += (1 + 6)
                    }
                }
            }
            sum += score
        }
        return sum
    }
}

fun main() {
    val input = readInput("Day2")

     println("part-1 answer: ${Day2().part1(input)}")
     println("part-2 answer: ${Day2().part2(input)}")

}
