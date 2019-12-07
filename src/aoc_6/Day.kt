package aoc_6
import RunAoc
import java.io.File
import java.util.*

class Day: RunAoc {
    val planets = mutableMapOf<String,Planet>().apply { this.put("COM",Planet("COM","0")) }

    class Planet(val name:String,val previous:String) {
        var orbits=0
        override fun toString(): String {
            return orbits.toString()
        }
    }

    override fun a(): Int {
        val lines= File("aoc_6/input_s.txt")
                .readLines()
                .map { it.split(")") }
                .associate { pairs -> pairs[1] to pairs[0]  }

        fun setOfPlanet(name:String) : MutableSet<String> {
            return if (name=="COM") mutableSetOf("COM") else setOfPlanet(lines[name]!!).apply { add(name) }
        }

        return lines.keys.sumBy { setOfPlanet(it).size - 1 }
    }

    override fun b():Int {
        return 0
    }
}

fun main() {
    val day=Day()
    println(day.a())
}