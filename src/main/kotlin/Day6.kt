fun main() {
    val input = readInput("Day6")

    val test = input[0]
    val size = 14
    val marker = test.windowed(size).first { it.toSet().size == size }
    val ans = test.indexOf(marker) + size
    println(ans)
}