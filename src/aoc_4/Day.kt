package aoc_4
import RunAoc
import java.io.File
import java.util.*


class Day: RunAoc {

    val input = 387638..919123

    fun matchA(password: String) = password.zipWithNext().any { it.first == it.second }
            && password.zipWithNext().all { it.first <= it.second }

    fun matchB(password: String) = password.zipWithNext().all { it.first <= it.second }
            && ('0'..'9').any { digit -> password.indexOfLast { it == digit } - password.indexOfFirst { it == digit } == 1 }

    override fun a(): Int {
        return input.count { matchA(it.toString())}
    }

    override fun b():Int {
        return input.count { matchB(it.toString())}
    }
}

fun main() {
    val day=Day()
    println(day.a())
}