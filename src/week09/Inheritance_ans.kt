package week09

// Vehicle이라는 상위클래스를 만들어서 전체 코드를 refactoring 하시오
abstract class Vehicle(val type: String) {
    var currentSpeed = 0
        protected set // 상속받은 클래스에서만 값을 변경할 수 있도록 설정

    protected abstract val defaultSpeed: Int

    open fun start() {
        println("[$type] I'm moving")
        currentSpeed = defaultSpeed
    }

    open fun stop() {
        println("[$type] Stopped")
        currentSpeed = 0
    }
}

class CarBasic : Vehicle("Car") {
    override val defaultSpeed = 10

    fun refuel() {
        println("[$type] Refuel complete")
    }
}

class BoatBasic : Vehicle("Boat") {
    override val defaultSpeed = 8

    fun anchor() {
        println("[$type] Dropped anchor")
    }
}

class AircraftBasic(val seats: Int) : Vehicle("Aircraft") {
    override val defaultSpeed = 15

    // 아래 stop은 다른 class와는 출력이 다르다는 점을 명시하시오.
    override fun stop() {
        println("[$type] Stopped at the gate");
        currentSpeed = 0
    }

    fun takeOff() {
        println("[$type] Taking off")
    }

    fun land() {
        println("[$type] Landed")
    }
}

fun main() {
    val car = CarBasic()
    val boat = BoatBasic()
    val aircraft = AircraftBasic(120)

    car.start(); car.refuel(); car.stop()
    boat.start(); boat.anchor(); boat.stop()
    aircraft.start(); aircraft.takeOff(); aircraft.land(); aircraft.stop()
}