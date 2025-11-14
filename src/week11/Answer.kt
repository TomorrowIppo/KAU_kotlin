package week11

import kotlin.math.PI
import kotlin.math.floor

fun main() {
    val inputs = listOf("12", 3, 4.7, "oops", null)
    val normalized = normalizeInputs(inputs)
    println("[1] normalizeInputs 결과: $normalized (예: [12, 3, 4])")

    val vid1 = VehicleId("TRIMM", "0001")
    val vid2 = VehicleId("TRIMM", "0001")
    println("[2] vid1==vid2 => ${vid1 == vid2}, set.size=${setOf(vid1, vid2).size}")

    val car = CarTransport(2.0, 1.5, vid1)
    val drone = DroneTransport(0.5, VehicleId("TRIMM", "D-01"))

    println("[3] car.move(5) => ${car.move(5)}")
    println("[3] drone.fly() => ${drone.fly()}")

    val amph = AmphibiousCar(VehicleId("TRIMM", "A-1"))
    println("[4] amph.move() => ${amph.move()}")
    println("[4] amph.sail() => ${amph.sail()}") // 추가 테스트

    println("===== 종료 =====")
}

// 1) 타입 검사와 캐스팅
// Any 객체의 리스트에서 Int화 시킬 수 있는 객체만 남겨 새 List에 넣어 반환한다.
// 실제 Int이면 그냥 사용
// Double이면 Int로 바꿔서 사용
// 문자열이면 toIntOrNull()이용해서 null이 아닌 경우만 사용
// item을 list에 넣는 법: result += item
fun normalizeInputs(raw: List<Any?>): List<Int> {
    val result = mutableListOf<Int>()
    // TODO: when을 사용해 Int/String/Double 구분 처리
    for (item in raw) {
        when (item) {
            is Int -> result += item
            is Double -> result += item.toInt()
            is String -> {
                item.toIntOrNull()?.let { result += it }
            }
        }
    }
    return result
}

// 2) Any 오버라이드
class VehicleId(val maker: String, val serial: String) {
    // TODO: equals, hashCode, toString 직접 구현
    override fun equals(other: Any?): Boolean {
        // 1. 메모리 주소가 같은지
        if (this === other) return true
        // 2. 타입이 다른지
        if (other !is VehicleId) return false
        // 3. 내용이 같은지
        return this.maker == other.maker && this.serial == other.serial
    }

    override fun hashCode(): Int = maker.hashCode() * 31 + serial.hashCode()

    override fun toString(): String = "VehicleId(maker='$maker', serial='$serial')"
}

// 3) 추상 클래스
abstract class Transport(val id: VehicleId) {
    abstract fun area(): Double
    abstract fun move(distance: Int): String
}

class CarTransport(val width: Double, val height: Double, id: VehicleId) : Transport(id), Rideable {
    // TODO: area, move, moveOnRoad 구현
    override fun area(): Double {
        return width * height
    }

    override fun move(distance: Int): String {
        return "Car ${id.serial} moves $distance km ${moveOnRoad()}"
    }

    override fun moveOnRoad(): String {
        return "길을 따라 달립니다."
    }
}

class DroneTransport(val radius: Double, id: VehicleId) : Transport(id), Flyable {
    // TODO: area, move, fly 구현
    override fun area(): Double {
        // 원의 넓이: PI * r^2
        return PI * radius * radius
    }

    override fun move(distance: Int): String {
        return "드론은 ${id.serial} $distance km 날 수 있어요~ ${fly()}"
    }

    override fun fly(): String {
        return "날아간다~"
    }
}

// 4) 인터페이스
interface Rideable { fun moveOnRoad(): String; fun move(): String = "move by road" }
interface Flyable { fun fly(): String }
interface Sailable { fun sail(): String; fun move():String = "move by sea" }

class AmphibiousCar(val id: VehicleId) : Rideable, Sailable {
    // TODO: moveOnRoad, sail, move(super<인터페이스> 중재) 구현
    override fun moveOnRoad(): String {
        return "Amphibious car ${id.serial} 길을 따라 달린다~"
    }

    override fun sail(): String {
        return "Amphibious car ${id.serial} 항해한다~"
    }

    override fun move(): String {
        val roadMove = super<Rideable>.move()
        val seaMove = super<Sailable>.move()
        return "차량은 $roadMove 하고 $seaMove 할 수 있습니다."
    }
}