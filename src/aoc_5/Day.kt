package aoc_5
import RunAoc
import java.io.File
import java.util.*


class Day: RunAoc {

    val init_memory:MutableList<Int> by lazy {resetMemory()}
    val INPUT_VALUE=1
    lateinit var ls:MutableList<Int>
    var control = ""

    fun testList(parls:MutableList<Int>) {
        ls=parls
    }

    private fun resetMemory(): MutableList<Int> {

        val sc= Scanner(File("aoc_5/input.txt")).useDelimiter(",")
        val init_ls = mutableListOf<Int>()
        while(sc.hasNextInt()){
            init_ls.add(sc.nextInt())
        }
        return init_ls
    }

    override fun a(): Int {
        ls= mutableListOf<Int>().apply { addAll(init_memory) }
//        ls[1]=12
//        ls[2]=2
        return instruct()
    }


    fun instruct(): Int {
        val it =ls.iterator()
        var i=0
        var tmpvalue=0
        loop@ while (it.hasNext()) {
            control=it.next().toString()
            val command = controllast(2)
            when(command){
                1 -> output(input(it)+input(it),it)
                2 -> output(input(it)*input(it),it)
                3 -> write(INPUT_VALUE,it)
                4 -> println(input(it))
                99 -> break@loop
            }
        }
        return ls[0]
    }

    fun controllast(x:Int=1):Int {
        val maxchar=if(x>control.length) control.length else x
        val opmode=control.takeLast(maxchar)
        control=control.substring(0,control.length-maxchar)
        return if(opmode.isBlank()) 0 else opmode.toInt()

    }

    fun input(it:Iterator<Int>):Int {
        return  if(controllast()==1) it.next() else ls[it.next()]
    }

    fun output(value:Int,it:Iterator<Int>) {
        if(controllast()==1) it.next() else ls[it.next()]=value
    }

    fun write(value:Int,it:Iterator<Int>) {
        ls[it.next()]=value
    }

    override fun b():Int {
        return 0
    }
}

fun main() {
    val day=Day()
//    return
//    day.testList(mutableListOf<Int>(3,2,4,0,99))
    day.testList(mutableListOf<Int>(1001,1,1,4,99,5,6,0,99))
    println(day.instruct())
}