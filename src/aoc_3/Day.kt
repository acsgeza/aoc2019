package aoc_3
import RunAoc
import java.io.File
import java.util.*


class Day: RunAoc {

    val input_ls by lazy { readPaths()}
    val mins by lazy { calc() }

    private fun readPaths(): List<String> {
        return File("aoc_3/input.txt").readLines()
    }

    enum class DIR(val x:Int, val y:Int){
        R(1,0),L(-1,0),U(0,1),D(0,-1)
    }

    override fun a(): Int {
        return mins.first
    }

    private fun calc(): Pair<Int, Int> {
        var sc = Scanner(input_ls.toMutableList()[0]).useDelimiter(",")
        var X = 0
        var Y = 0
        var S = 0
        val first_steps = mutableMapOf<Pair<Int, Int>, Int>()
        val coords = mutableSetOf<Pair<Int, Int>>()
        while (sc.hasNext()) {
            val next = sc.next()
            val command = next[0].toString()
            val coord = next.drop(1).toInt()
            for (j in 1..coord) {
                X += DIR.valueOf(command).x
                Y += DIR.valueOf(command).y
                S++
                first_steps[Pair(X, Y)] = S
                coords.add(Pair(X, Y))
            }
        }

        X = 0
        Y = 0
        S = 0
        val intersections = mutableSetOf<Triple<Int, Int, Int>>()
        sc = Scanner(input_ls.toMutableList()[1]).useDelimiter(",")
        while (sc.hasNext()) {
            val next = sc.next()
            val command = next[0].toString()
            val coord = next.drop(1).toInt()
            for (j in 1..coord) {
                X += DIR.valueOf(command).x
                Y += DIR.valueOf(command).y
                S++
                if (coords.contains(Pair(X, Y))) intersections.add(Triple(X, Y, S + first_steps.getValue(Pair(X, Y))))
            }
        }
        val mindist = intersections.map { Math.abs(it.first) + Math.abs(it.second) }.min() ?: 0
        val minstep = intersections.map { it.third }.min() ?: 0
        return Pair(mindist,minstep)
    }


    override fun b():Int {
        return mins.second
    }
}

fun main() {
    val day=Day()
    println(day.a())
}