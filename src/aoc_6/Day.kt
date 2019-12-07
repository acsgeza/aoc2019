package aoc_6
import RunAoc
import java.io.File
class Day: RunAoc {
    val lines= File("aoc_6/input.txt")
        .readLines()
        .map { it.split(")") }
        .associate { pairs -> pairs[1] to pairs[0]  }
    fun setOfPlanet(name:String) : MutableSet<String> {
        return if (name=="COM") mutableSetOf("COM") else setOfPlanet(lines[name]!!).apply { add(name) }
    }
    override fun a(): Int = lines.keys.sumBy { setOfPlanet(it).size - 1 }

    override fun b():Int= (setOfPlanet("YOU")-setOfPlanet("SAN")).size+(setOfPlanet("SAN")-setOfPlanet("YOU")).size-2
}

fun main() {
    val day=Day()
    println(day.b())

}