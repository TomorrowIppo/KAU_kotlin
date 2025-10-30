// Vehicle이라는 상위클래스를 만들어서 전체 코드를 refactoring 하시오

class CarBasic {
    var currentSpeed = 0
    fun start() { println("[Car] I'm moving"); currentSpeed = 10 }
    fun stop() { println("[Car] Stopped"); currentSpeed = 0 }
    fun refuel() { println("[Car] Refuel complete") }
}

class BoatBasic {
    var currentSpeed = 0
    fun start() { println("[Boat] I'm moving"); currentSpeed = 8 }
    fun stop() { println("[Boat] Stopped"); currentSpeed = 0 }
    fun anchor() { println("[Boat] Dropped anchor") }
}

class AircraftBasic(val seats: Int) {
    var currentSpeed = 0
    fun start() { println("[Aircraft] I'm moving"); currentSpeed = 15 }
    // 아래 stop은 다른 class와는 출력이 다르다는 점을 명시하시오.    
    fun stop() { println("[Aircraft] Stopped at the gate"); currentSpeed = 0 }
    fun takeOff() { println("[Aircraft] Taking off") }
    fun land() { println("[Aircraft] Landed") }
}

//fun main() {
//    val car = CarBasic()
//    val boat = BoatBasic()
//    val aircraft = AircraftBasic(120)
//
//    car.start(); car.refuel(); car.stop()
//    boat.start(); boat.anchor(); boat.stop()
//    aircraft.start(); aircraft.takeOff(); aircraft.land(); aircraft.stop()
//}
