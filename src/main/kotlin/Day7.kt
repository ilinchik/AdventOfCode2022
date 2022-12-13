import java.lang.Integer.min

fun main() {
    val input = readInput("Day7")

    val test = """
        ${'$'} cd /
        ${'$'} ls
        dir a
        14848514 b.txt
        8504156 c.dat
        dir d
        ${'$'} cd a
        ${'$'} ls
        dir e
        29116 f
        2557 g
        62596 h.lst
        ${'$'} cd e
        ${'$'} ls
        584 i
        ${'$'} cd ..
        ${'$'} cd ..
        ${'$'} cd d
        ${'$'} ls
        4060174 j
        8033020 d.log
        5626152 d.ext
        7214296 k
    """.trimIndent().split("\n")

    data class File(val name: String, val size: Int)

    data class Dir(
        val name: String,
        val parent: Dir? = null,
        val files: HashMap<String, File> = HashMap(),
        val dirs: HashMap<String, Dir> = HashMap()
    ) {
        fun totalSize(): Int {
            val fs = files.values.sumOf { it.size }
            val ds = dirs.values.sumOf { it.totalSize() }
            return fs + ds
        }
    }

    val root = Dir("/")
    var cur = root

    input.forEach {
        val cmd = it.split(" ")
        when {
            it.startsWith("$ cd") -> {
                cur = when (it) {
                    "$ cd /" -> root
                    "$ cd .." -> cur.parent!!
                    else -> cur.dirs.get(cmd[2])!!
                }
            }
            it.startsWith("dir") -> {
                val dirName = cmd[1]
                cur.dirs.putIfAbsent(dirName, Dir(dirName, cur))
            }
            it.startsWith("$ ls") -> {}
            else -> cur.files.put(cmd[1], File(cmd[1], cmd[0].toInt()))
        }
    }

    var sum = 0
    fun findTotal(dir: Dir) {
        if (dir.totalSize() <= 100_000) {
            sum += dir.totalSize()
        }
        for (d in dir.dirs.values) {
            findTotal(d)
        }
    }
    findTotal(root)
    println("answer part-1: $sum")

    val diskSpace = 70_000_000
    val updateSize = 30_000_000
    val unused = diskSpace - root.totalSize()
    val rem = updateSize - unused;

    println("unused: $unused need more: ${updateSize - unused}")

    var delete = root.totalSize()

    fun findDirForDeletion(dir : Dir) {
        if (dir.totalSize() >= rem) {
            delete = min(delete, dir.totalSize())
        }
        for (d in dir.dirs.values) {
            findDirForDeletion(d)
        }
    }
    findDirForDeletion(root)
    println("answer part-2: $delete")
}
