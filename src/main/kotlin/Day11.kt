class Monkey(var items: MutableList<Long>, val expr: String, val test: Long, val m1: Int, val m2: Int) {
    var total: Long = 0L

    override fun toString(): String {
        return """
            Monkey:
              Starting items: $items
              Operation: new = $expr
              Test: divisible by $test
                If true: throw to monkey $m1
                If false: throw to monkey $m2
        """.trimIndent()
    }

    private fun eval(expr: String): Long {
        val a = expr.split(" ")[0].toLong()
        val b = expr.split(" ")[2].toLong()
        val op = expr.split(" ")[1]
        return when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> a / b
            else -> throw RuntimeException("Invalid expr: $expr")
        }
    }

    fun turn(registry: HashMap<Int, Monkey>, m: Long) {
        items.forEach {
            val wl = eval(expr.replace("old", it.toString())) % m

            if (wl % test == 0L) {
                registry[m1]?.items?.add(wl)
            } else {
                registry[m2]?.items?.add(wl)
            }
        }
        total += items.size
        items.clear()
    }
}


fun main() {
    val input = readInput("Day11")

    val test = """
        Monkey 0:
          Starting items: 79, 98
          Operation: new = old * 19
          Test: divisible by 23
            If true: throw to monkey 2
            If false: throw to monkey 3
        
        Monkey 1:
          Starting items: 54, 65, 75, 74
          Operation: new = old + 6
          Test: divisible by 19
            If true: throw to monkey 2
            If false: throw to monkey 0
        
        Monkey 2:
          Starting items: 79, 60, 97
          Operation: new = old * old
          Test: divisible by 13
            If true: throw to monkey 1
            If false: throw to monkey 3
        
        Monkey 3:
          Starting items: 74
          Operation: new = old + 3
          Test: divisible by 17
            If true: throw to monkey 0
            If false: throw to monkey 1
    """.trimIndent().split("\n")


    var id = 0
    val registry = HashMap<Int, Monkey>()

    input.chunked(7).forEach {
        val items = it[1].substring(it[1].indexOf(":") + 1)
            .trim()
            .split(",")
            .map { i -> i.trim().toLong() }
            .toMutableList()

        val expr = it[2].substring(it[2].indexOf("=") + 1).trim()
        val t = it[3].split(" ").last().toLong()
        val m1 = it[4].split(" ").last().toInt()
        val m2 = it[5].split(" ").last().toInt()
        registry[id++] = Monkey(items, expr, t, m1, m2)
    }

    val m = registry.values.map { it.test }.reduce { p, i -> p * i }

    var r = 0
    for (i in 1..10_000) {
        registry.values.forEach {
            it.turn(registry, m)
        }

    }

    val ans = registry.values.sortedByDescending { it.total }.take(2).map { it.total }

    println("answer part-2 ${ans[0] * ans[1]}")
}