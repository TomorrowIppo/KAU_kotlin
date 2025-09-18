package week02

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException

fun main() {
    try {
        val reader = BufferedReader(FileReader("students.txt"))

        try {
            val numStudents = reader.readLine()!!.toInt()
            for (i in 0..<numStudents) {
                val name = reader.readLine()!!
                val point = reader.readLine()!!.toInt()

                fun printGrade() {
                    // refactor
                    val grade = when {
                        point >= 90 -> "A"
                        point in 80..89 -> "B"
                        point in 70..79 -> "C"
                        point in 60..69 -> "D"
                        else -> "F"
                    }

                    println("$name has grade $grade")
                }

                printGrade()
            }
        } catch (e: IOException) {
            println("File I/O Error")
        } finally {
            reader.close()
        }
    } catch(e: FileNotFoundException) {
        println("File Not Found")
    }
}