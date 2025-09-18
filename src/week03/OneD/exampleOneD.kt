package week03.OneD

fun main() {
    val car = Car("12가1234", 2020, 1.0, 2.0)

    car.attitude.passTime(1.0)
    car.attitude.velocity = 2.0
    car.attitude.passTime(2.0)

    println("${car.number} is now at ${car.attitude.position}")
}