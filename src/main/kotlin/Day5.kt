class Day5 {

    fun part1(lines: List<String>): String {
        val stacks = parseStacks(lines)
        val commands = lines.drop(maxHeight + 2)
        commands.forEach {
            val c = it.split(" ")
            val amount = c[1].toInt()
            val from = c[3].toInt() - 1
            val to = c[5].toInt() - 1

            stacks[to].addAll(0, stacks[from].take(amount).reversed()) // part2
            stacks[from] = stacks[from].drop(amount).toMutableList()
        }

        return stacks.map { it[0] }.fold("") { res, ch -> res.plus(ch) }
    }

    private fun parseStacks(lines: List<String>): Array<MutableList<Char>> {
        val stacks = Array(numOfStacks) { mutableListOf<Char>() }

        for (line in lines.take(maxHeight)) {
            for (c in 1..line.length step 4) {
                if (line[c] in 'A'..'Z') {
                    stacks[c / 4].add(line[c])
                }
            }
        }
        return stacks
    }

}

private const val numOfStacks = 9
private const val maxHeight = 8

fun main() {

    val input = readInput("Day5")
    println("part-1 answer: ${Day5().part1(input)}")

    // JDTMRWCQJ
}