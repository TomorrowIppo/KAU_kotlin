package week02

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader

fun main() {
    try {
        val reader = BufferedReader(FileReader("students.txt"))

        try {
            val numStudents = reader.readLine()!!.toInt()

            // 1 .. num과 0 until num 차이 알아두기!!!!!!!!
            for (i in 1 .. numStudents) {
                val name = reader.readLine()!!
                val points = reader.readLine()!!.toInt()

                fun printGrade() {
                    val grade = when (points) {
                        in 90 .. 100 -> "A"
                        in 80 .. 89 -> "B"
                        in 70 .. 79 -> "C"
                        in 60..<70 -> "D"
                        else -> "F"
                    }
                    println("$name has grade $grade point.")
                }
            }
        } catch (e: Exception) {
            println("File I/O Error")
        } finally {
            reader.close()
        }


    } catch (e: FileNotFoundException) {
        println("File not found.")
    }
}