package aoc_8
import RunAoc
import java.io.File
import java.util.*

const val WIDE=25
const val TALL=6
class Day: RunAoc {

    override fun a(): Int {
        val content=File("aoc_8/input.txt").readText()
        var i=0; var j=0;var remain=content.length
        while(j+1<remain) {
            j=i+WIDE
            println(content.substring(i,j)+" Remain:"+(remain-j))
            i=j
        }
        return 0
    }

    override fun b():Int {
        return 0
    }
}

fun main() {
    val day=Day()
    println(day.a())
}