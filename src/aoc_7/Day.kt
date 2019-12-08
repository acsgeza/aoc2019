package aoc_7

import RunAoc
import java.io.File
import java.util.*

val SEQUENCE = listOf(0, 1, 2, 3, 4)

class Day : RunAoc {
    private val initial: MutableList<Int> by lazy { resetMemory() }
    private var inputVALUE = 0
    private var amplifier: Int? = 0
    lateinit var ls: MutableList<Int>
    var pointer = 0
    var control = ""

    private fun resetMemory(): MutableList<Int> {

        val sc = Scanner(File("aoc_7/input.txt")).useDelimiter(",")
        val init_ls = mutableListOf<Int>()
        while (sc.hasNextInt()) {
            init_ls.add(sc.nextInt())
        }
        return init_ls
    }

    override fun a(): Int {
        return instruct(initial)
    }


    fun instruct(original: MutableList<Int>): Int {
        var maxOutput=0
        permute((0..4).toList()).forEach {

            it.forEach {
                ls = mutableListOf<Int>().apply { addAll(original) }
                amplifier = it
                var i = 0
                var tmpvalue = 0
                pointer = 0
                loop@ while (pointer <= ls.size) {
                    control = ls[pointer++].toString()
                    val command = controllast(2)
                    when (command) {
                        1 -> write(read() + read())
                        2 -> write(read() * read())
                        3 -> {
                            val what = amplifier ?: inputVALUE
                            input(what)
                            amplifier = null
                        }
                        4 -> inputVALUE = read()
                        5 -> jumpTrue()
                        6 -> jumpFalse()
                        7 -> lessThan()
                        8 -> equalPars()
                        99 -> break@loop
                    }
//            println("Pointer:"+pointer+"  Value:"+ls[pointer])
                }
            }
            if(inputVALUE>maxOutput) maxOutput=inputVALUE
            inputVALUE=0
        }
        return maxOutput
    }

    private fun equalPars() = write(if (read() == read()) 1 else 0)

    private fun lessThan() = write(if (read() < read()) 1 else 0)


    private fun jumpTrue() {
        if (read() != 0) pointer = read() else pointer++
    }

    private fun jumpFalse() {
        if (read() == 0) pointer = read() else pointer++
    }


    fun controllast(x: Int = 1): Int {
        val maxchar = if (x > control.length) control.length else x
        val opmode = control.takeLast(maxchar)
        control = control.substring(0, control.length - maxchar)
        return if (opmode.isBlank()) 0 else opmode.toInt()

    }

    fun read(): Int {
        return if (controllast() == 1) ls[pointer++] else ls[ls[pointer++]]
    }

    fun write(value: Int) {
        if (controllast() == 1) ls[pointer++] = value else ls[ls[pointer++]] = value
    }

    fun input(value: Int) {
        ls[ls[pointer++]] = value
    }

    override fun b(): Int {
        return 0
    }
}

fun <T> permute(input: List<T>): List<List<T>> {
    if (input.size == 1) return listOf(input)
    val perms = mutableListOf<List<T>>()
    val toInsert = input[0]
    for (perm in permute(input.drop(1))) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(newPerm)
        }
    }
    return perms
}

fun main() {
    val day = Day()
    println(day.instruct(mutableListOf(3,23,3,24,1002,24,10,24,1002,23,-1,23,
        101,5,23,23,1,24,23,23,4,23,99,0,0)))

}