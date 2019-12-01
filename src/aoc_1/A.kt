package aoc_1
import RunAoc
import java.io.File

class Day(): RunAoc {
    fun fuel(mass:Int) = (Math.floor(mass/3.0)-2).toInt()

    override fun a():Int {
        return File("aoc_1/input_a.txt").readLines()
            .filter(String::isNotEmpty)
            .map(String::toInt).sumBy { fuel(it)}
    }

    fun recfuel(mass:Int):Int {
        var total =0
        val f=fuel(mass)
        total += if (f>0) f+recfuel(f) else 0
        return total
    }

    override fun b():Int {
        return File("aoc_1/input_a.txt").readLines()
            .filter(String::isNotEmpty)
            .map(String::toInt).sumBy { recfuel(it)}
    }
}

fun main() {
    val day=Day()
    println(day.recfuel(100756))
}

