package aoc_2
import RunAoc
import java.io.File
import java.util.*
import java.util.Collections.addAll


class Day: RunAoc {
    val init_memory:MutableList<Int> by lazy {resetMemory()}
    private fun resetMemory(): MutableList<Int> {

            val sc= Scanner(File("aoc_2/input.txt")).useDelimiter(",")
            val ls = mutableListOf<Int>()
            while(sc.hasNextInt()){
                ls.add(sc.nextInt())
            }
            return ls
    }

    override fun a(): Int {
        val ls= mutableListOf<Int>().apply { addAll(init_memory) }
        ls[1]=12
        ls[2]=2
        return alarm(ls)
    }

     fun alarm(ls: MutableList<Int>): Int {
         val it =ls.iterator()
         var i=0
         while (it.hasNext()) {

             val command=it.next()
             if (command==99) break
             val first=it.next()
             val second=it.next()
             val output=it.next()
             when(command){
                 1 -> ls[output]=ls[first]+ls[second]
                 2 -> ls[output]=ls[first]*ls[second]
             }
         }
         return ls[0]
    }

    override fun b():Int {
        for (noun in 0..99){
            for(verb in 0..99){
                val ls= mutableListOf<Int>().apply { addAll(init_memory) }
                ls[1]=noun
                ls[2]=verb
                val alarm=alarm(ls)
                if(alarm==19690720) return 100*noun+verb
            }
        }
        return 0
    }
}

fun main() {
    val day=Day()
    println(day.alarm(mutableListOf<Int>(1,1,1,4,99,5,6,0,99)))
}