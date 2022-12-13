import java.lang.Integer.max

fun main() {
    val input = readInput("Day8")

    val test = """
        30373
        25512
        65332
        33549
        35390
    """.trimIndent()
        .split("\n")

    val rows = input.size
    val cols = input[0].length
    val m = Array(cols) { IntArray(rows) }

    for ((i, v) in input.withIndex()) {
        m[i] = v.map { ch -> ch.digitToInt() }.toIntArray()
    }

    val v = mutableSetOf<Pair<Int, Int>>()
    // l -> r and l <- r
    for (r in 0 until rows) {
        var maxL = m[r][0]
        v.add(Pair(r, 0))
        var maxR = m[r][cols - 1]
        v.add(Pair(r, cols - 1))

        for (c in 1 until cols) {
            if (maxL < m[r][c]) {
                maxL = m[r][c]
                v.add(Pair(r, c))
            }
            if (maxR < m[r][cols - c - 1]) {
                maxR = m[r][cols - c - 1]
                v.add(Pair(r, cols - c - 1))
            }
        }
    }

    for (c in 0 until cols) {
        var maxT = m[0][c]
        v.add(Pair(0, c))
        var maxB = m[rows - 1][c]
        v.add(Pair(rows - 1, c))

        for (r in 1 until rows) {
            if (maxT < m[r][c]) {
                maxT = m[r][c]
                v.add(Pair(r, c))
            }
            if (maxB < m[rows - r - 1][c]) {
                maxB = m[rows - r - 1][c]
                v.add(Pair(rows - r - 1, c))
            }
        }
    }

    println("answer part-1: ${v.size}")

    fun viewDist(r: Int, c: Int, nr: Int, nc: Int, direction: Direction): Int {
        if (nr < 0 || nr == rows) return 0
        if (nc < 0 || nc == cols) return 0

        if (m[r][c] <= m[nr][nc]) return 1

        return 1 + when (direction) {
            Direction.LEFT -> viewDist(r, c, nr, nc - 1, Direction.LEFT)
            Direction.RIGHT -> viewDist(r, c, nr, nc + 1, Direction.RIGHT)
            Direction.TOP -> viewDist(r, c, nr - 1, nc, Direction.TOP)
            Direction.BOTTOM -> viewDist(r, c, nr + 1, nc, Direction.BOTTOM)
        }
    }

    fun score(r: Int, c: Int): Int {
        return viewDist(r, c, r, c - 1, Direction.LEFT) *
                viewDist(r, c, r, c + 1, Direction.RIGHT) *
                viewDist(r, c, r - 1, c, Direction.TOP) *
                viewDist(r, c, r + 1, c, Direction.BOTTOM)
    }

    var score = 0
    for (r in 0 until rows) {
        for (c in 0 until cols) {
            val s = score(r, c)
            score = max(s, score)
        }
    }
    println("answer part-2: $score")
}

enum class Direction {
    LEFT, RIGHT, TOP, BOTTOM
}