//package week02
//
//import java.io.BufferedReader
//import java.io.FileNotFoundException
//import java.io.FileReader
//import java.io.IOException
//
////fun max(a: Int, b: Int): Int = if(a > b) a else b
//
//fun toInt(str: String): Int {
//
//    var result:Int = 0
//    for (i in str.indices) {
//        result *= 10
//
//        if (str[i] <'0' || str[i] > '9') throw NumberFormatException("No Number")
//        result += str[i] - '0'
//    }
//    return result
//}
//
//fun main() {
//    try {
//        val reader = BufferedReader(FileReader("students.txt"))
//
//        try {
//            println(reader.readLine()!!)
//        } catch (e: IOException) {
//            println("파일 작업중 문제 발생")
//        } finally {
//            reader.close()
//        }
//    } catch(e: FileNotFoundException) {
//        println("could not find file")
//    }
//
//    try {
//        println("234는 정수로 ${toInt("123")}")
//    } catch (e: NumberFormatException) {
//        println(e.message)
//        println("숫자만 입력해주세요")
//    } finally { // 무조건 실행
//        println("무조건")
//    }
//}
