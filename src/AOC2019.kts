
import java.time.LocalDate
import kotlin.reflect.full.createInstance

val TODAY = false
val runday= if (TODAY) LocalDate.now() else LocalDate.parse("2019-12-05")
val day =runday.dayOfMonth

val aoc_Day= Class.forName("aoc_${day}.Day").kotlin.createInstance()

val a = aoc_Day.javaClass.getMethod("a")
println("A:"+a.invoke(aoc_Day))


val b = aoc_Day.javaClass.getMethod("b")
println("B:"+b.invoke(aoc_Day))