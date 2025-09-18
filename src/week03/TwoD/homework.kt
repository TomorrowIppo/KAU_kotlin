package week03.TwoD

fun main() {
    val car = Car("23가1234", 2020)
    car.attitude.position = TwoDValue(1.0, 1.0)
    car.attitude.setVelocity(1.0, 1.0)
    car.attitude.passTime(1.0)
    car.attitude.setDirectionAndSpeed(1.57, 2.0)
    car.attitude.passTime(2.0)
    println("${car.number} is now at (${car.attitude.position.x}, ${car.attitude.position.y})")
}