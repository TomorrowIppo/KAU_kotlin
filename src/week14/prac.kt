package week14

import java.io.File
import java.io.FileNotFoundException
import java.util.*
import kotlin.math.PI

abstract class Shape {
    abstract val area: Double

    abstract override fun toString(): String
}

class Triangle(val width: Double, val height: Double) : Shape() {
    override val area: Double
        get() = (width * height) / 2.0

    override fun toString(): String {
        return "삼각형: 가로: $width, 세로: $height, 면적: $area"
    }
}

class Rectangle(val width: Double, val height: Double) : Shape() {
    override val area: Double
        get() = width * height

    override fun toString(): String {
        return "직사각형: 가로: $width, 세로: $height, 면적: $area"
    }
}

class Circle(val diameter: Double) : Shape() {
    val radius: Double
        get() = diameter / 2.0

    override val area: Double
        get() = PI * radius * radius

    override fun toString(): String {
        return "원: 지름: $diameter, 반지름: $radius, 면적: $area"
    }
}

fun main() {
    // 4개의 리스트를 모두 사용
    val triangleList: MutableList<Triangle> = mutableListOf()
    val rectangleList: MutableList<Rectangle> = mutableListOf()
    val circleList: MutableList<Circle> = mutableListOf()
    val shapeList: MutableList<Shape> = mutableListOf() // 다형성 확인용 리스트

    val filePath = "src/week14/shapes.txt"
    val file = File(filePath)

    println("--- 파일 읽기 시작 ---")

    try {
        if (!file.exists()) {
            // 파일이 존재하지 않는 경우
            throw FileNotFoundException("파일을 찾을 수 없습니다: $filePath")
        }
        file.useLines { lines ->
            lines.forEach { line ->
                if (line.isBlank()) return@forEach

                val parts = line.split(" ")
                if (parts.size < 2) {
                    println("경고: 형식 오류 감지됨 - $line")
                    return@forEach
                }

                val type = parts[0].lowercase(Locale.getDefault())

                when (type) {
                    "triangle" -> {
                        if (parts.size == 3) {
                            val width = parts[1].toDoubleOrNull()
                            val height = parts[2].toDoubleOrNull()
                            if (width != null && height != null) {
                                val tri = Triangle(width, height)
                                triangleList.add(tri)
                                shapeList.add(tri)
                            }
                        }
                    }

                    "rectangle" -> {
                        if (parts.size == 3) {
                            val width = parts[1].toDoubleOrNull()
                            val height = parts[2].toDoubleOrNull()
                            if (width != null && height != null) {
                                val rec = Rectangle(width, height)
                                rectangleList.add(rec)
                                shapeList.add(rec)
                            }
                        }
                    }

                    "circle" -> {
                        if (parts.size == 2) {
                            val diameter = parts[1].toDoubleOrNull()
                            if (diameter != null) {
                                val cir = Circle(diameter)
                                circleList.add(cir)
                                shapeList.add(cir)
                            }
                        }
                    }

                    else -> {
                        println("경고: 알 수 없는 도형 타입 감지됨 - $type")
                    }
                }
            }
        }
    } catch (e: Exception) {
        println("파일 처리 중 오류가 발생: ${e.message}")
        return
    }

    fun printList(title: String, list: List<*>) {
        println("$title")
        if (list.isEmpty()) {
            println("저장된 객체가 없습니다.")
            return
        }

        list.forEach { println(it) }
        println()
    }

    printList("1. Triangle 리스트", triangleList)
    printList("2. Rectangle 리스트", rectangleList)
    printList("3. Circle 리스트", circleList)
    printList("4. 모든 도형 리스트 (Shape 리스트 - 다형성 확인)", shapeList)
}