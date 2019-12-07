package aoc_5
import RunAoc
import java.io.File
import java.util.*


class Day: RunAoc {

    val init_memory:MutableList<Int> by lazy {resetMemory()}
    val INPUT_VALUE=5
    lateinit var ls:MutableList<Int>
    var pointer=0
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
        return instruct()
    }


    fun instruct(): Int {

        var i=0
        var tmpvalue=0
        loop@ while (pointer<=ls.size) {
            control=ls[pointer++].toString()
            val command = controllast(2)
            when(command){
                1 -> write(read()+read())
                2 -> write(read()*read())
                3 -> input(INPUT_VALUE)
                4 -> println(read())
                5 -> jumpTrue()
                6 -> jumpFalse()
                7 -> lessThan()
                8 -> equalPars()
                99 -> break@loop
            }
//            println("Pointer:"+pointer+"  Value:"+ls[pointer])
        }
        return ls[0]
    }

    private fun equalPars() = write(if(read()==read())  1 else 0)

    private fun lessThan() = write(if(read()<read())  1 else 0)


    private fun jumpTrue()  { if(read()!=0) pointer=read() else pointer++ }
    private fun jumpFalse() { if(read()==0) pointer=read() else pointer++ }


    fun controllast(x:Int=1):Int {
        val maxchar=if(x>control.length) control.length else x
        val opmode=control.takeLast(maxchar)
        control=control.substring(0,control.length-maxchar)
        return if(opmode.isBlank()) 0 else opmode.toInt()

    }

    fun read():Int {
        return  if(controllast()==1) ls[pointer++] else ls[ls[pointer++]]
    }

    fun write(value:Int) {
        if(controllast()==1) ls[pointer++]=value else ls[ls[pointer++]]=value
    }

    fun input(value:Int) {
        ls[ls[pointer++]]=value
    }

    override fun b():Int {
        return 0
    }
}

fun main() {
    val day=Day()
//    return
//    day.testList(mutableListOf<Int>(3,2,4,0,99))
    day.testList(mutableListOf<Int>(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
        1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
        999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99))
    println(day.instruct())
}