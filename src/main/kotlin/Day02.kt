import java.io.File

val opponentChoices = mapOf(
    "A" to Choice.ROCK,
    "B" to Choice.PAPER,
    "C" to Choice.SCISSORS
)

val myChoices = mapOf(
    "X" to Choice.ROCK,
    "Y" to Choice.PAPER,
    "Z" to Choice.SCISSORS
)

val roundResults = mapOf(
    "X" to RoundResult.LOSE,
    "Y" to RoundResult.DRAW,
    "Z" to RoundResult.WIN
)

enum class Choice(val weight: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

enum class RoundResult {
    LOSE,
    DRAW,
    WIN
}

val beat = mapOf(
    Choice.ROCK to Choice.SCISSORS,
    Choice.PAPER to Choice.ROCK,
    Choice.SCISSORS to Choice.PAPER
)

val loose = beat.entries.associate { it.value to it.key }

fun main(args: Array<String>) {
//    partOne()
    partTwo()
}

private fun partOne() {
    val file = Dummy::class.java.getResource("/day02/input.txt").file
    var total = 0
    File(file).readLines().forEach {
        val (op, m) = it.split(" ")
        val opponentChoice = opponentChoices[op]
        val myChoice = myChoices[m]!!
        total += myChoice.weight
        val roundResult = if (myChoice == opponentChoice) {
            3
        } else if (beat[myChoice] == opponentChoice) {
            6
        } else {
            0
        }
        total += roundResult
    }

    println(total)
}

private fun partTwo() {
    val file = Dummy::class.java.getResource("/day02/input.txt").file
    var total = 0
    File(file).readLines().forEach {
        val (op, m) = it.split(" ")
        val opponentChoice = opponentChoices[op]
        val myChoice = when (roundResults[m]) {
            RoundResult.LOSE -> {
                total += 0
                beat[opponentChoice]
            }

            RoundResult.DRAW -> {
                total += 3
                opponentChoice
            }

            else -> {
                total += 6
                loose[opponentChoice]
            }
        }
        total += myChoice!!.weight
    }

    println(total)
}
