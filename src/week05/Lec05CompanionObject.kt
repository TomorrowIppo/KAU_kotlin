package week05

// Companion Object를 활용해서 Factory Pattern 기법 구현

open class Vehicle() {
    companion object {
        fun make(text: String, numOfWheels: Int): Vehicle {
            return when(numOfWheels) {
                2 -> MotorCycle(text)
                4 -> Car(text)
                else -> throw IllegalArgumentException()
            }
        }
    }
}

class Car(val name: String): Vehicle() {}

class MotorCycle(val id: String): Vehicle() {}

fun main() {
    val v = Vehicle.make("12가4567", 2)
    println(v)
}