import java.io.File

fun main(args: Array<String>) {
//    getBiggestSum()
    getTo3BiggestSum()
}

private fun getBiggestSum() {
    val file = Dummy::class.java.getResource("/day01/input.txt").file
    var a = 0
    var b = 0
    File(file).readLines().forEach {
        if (it.isBlank()) {
            if (a < b) {
                a = b
            }
            b = 0
        } else {
            b += it.toInt()
        }
    }
    println(maxOf(a, b))
}

private fun getTo3BiggestSum() {
    val file = Dummy::class.java.getResource("/day01/input.txt").file
    var sum = 0
    val topList = File(file).readLines().foldIndexed(ArrayList<Int>()) { idx, topList, candidate ->
        if (candidate.isNotBlank()) {
            sum += candidate.toInt()
        }
        if (candidate.isBlank() || idx == File(file).readLines().size - 1) {
            topList.add(sum)
            topList.sortDescending()
            if (topList.size > 3) {
                topList.removeAt(3)
            }
            sum = 0
        }
        topList
    }

    println(topList.sum())
}
