import java.io.File

fun main(args: Array<String>) {
//    partOne()
    partTwo()
}

private fun partOne() {
    val weights = getWeights()
    val file = Dummy::class.java.getResource("/day03/input.txt").file
    val commonChars = ArrayList<Char>()
    File(file).readLines().forEach {
        val firstHalf = it.substring(0, it.length / 2).toCharArray().toSet()
        val secondHalf = it.substring(it.length / 2).toCharArray().toSet()
        commonChars.addAll(firstHalf.intersect(secondHalf))
    }
    val total = commonChars.sumOf { weights[it]!! }
    println(total)
}

private fun partTwo() {
    val weights = getWeights()
    val file = Dummy::class.java.getResource("/day03/input.txt").file
    val commonChars = ArrayList<Char>()
    File(file).readLines().chunked(3).forEach {
        commonChars.addAll(it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet()))
    }
    val total = commonChars.sumOf { weights[it]!! }
    println(total)
}

private fun getWeights(): Map<Char, Int> {
    val letters = ArrayList<Char>(52)
    for (i in ('a'..'z')) letters.add(i)
    for (i in ('A'..'Z')) letters.add(i)
    return letters.mapIndexed { idx, c -> c to idx + 1 }.toMap()
}
