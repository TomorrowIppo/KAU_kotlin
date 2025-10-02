package week04

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class TwoDValue(var x: Double, var y: Double) {
    // Lab: (x좌표, y좌표)를 반환하도록 함수를 채우시오
    override fun toString(): String = "($x, $y)"
}

class TwoDAttitude(val position: TwoDValue,
                   val velocity: TwoDValue) {
    fun passTime(time: Double) {
        /* Lab: time이 0보다 작거나 같은 경우 IllegalArgumentException을 던지게 하시오 */
        if (time <= 0) throw IllegalArgumentException() // 구현

        position.x += velocity.x * time
        position.y += velocity.y * time
    }

    fun setPosition(x: Double, y: Double) {
        position.x = x
        position.y = y
    }

    fun setVelocity(x: Double, y: Double) {
        velocity.x = x
        velocity.y = y
    }

    fun setDirectionAndSpeed(theta: Double, speed: Double) {
        /* Lab: speed가 0보다 작은 경우 IllegalArgumentException을 던지게 하시오 */
        if (speed <= 0) throw IllegalArgumentException()    // 구현

        velocity.x = speed * cos(theta)
        velocity.y = speed * sin(theta)
    }

    // Lab: direction 프로퍼티는 velocity의 x와 y 값에 종속되어 유추되어야 하며,
    //      스스로 자신의 값을 가져서는 안된다. 즉 이 프로퍼티를 참조하면 현재 velocity
    //      방향의 radian 각도를 반환하도록 만드시오.
    //      또한, 프로퍼티 direction을 바꾸면, 속도의 절대값은 그대로 둔채로, 방향만
    //      바뀌도록 velocity의 x와 y를 적절하게 설정하도록 만드시오.
    // 참고: 속도의 절대값: sqrt(vx * vx + vy *vy)
    //      방향의 radian 각도: atan2(vy, vx)
    var direction: Double   // 구현
        get() = atan2(velocity.y, velocity.x)
        set(value) {
            val s = speed
            velocity.x = s * cos(value)
            velocity.y = s * sin(value)

            /*

                주의!!!!!!
                만약
                velocity.x = speed * cos(value)라고 구현하는 순간
                연산하는 과정에서 speed의 값이 실시간으로 바뀌기 때문에 의도한 대로 작동 안할 수 있음.

             */
        }

    // Lab: speed 프로퍼티는 velocity의 x와 y 값에 종속되어 유추되어야 하며,
    //      스스로 자신의 값을 가져서는 안된다. 즉 이 프로퍼티를 참조하면 현재
    //      velocity(속도)의 절대값을 반환하도록 만드시오
    //      또한, 프로퍼티 speed를 바꾸면, 방향은 그대로 둔채, 속도의 절대값만
    //      바뀌도록 만드시오.
    // 참고: 속도의 절대값: sqrt(vx * vx + vy *vy)
    //      방향의 radian 각도: atan2(vy, vx)
    var speed: Double   // 구현
        get() = sqrt(velocity.x * velocity.x + velocity.y * velocity.y)
        set(value) {
            val dir = direction
            velocity.x = value * cos(dir)
            velocity.y = value * sin(dir)
        }
}

class Car(val number: String, val makingYear: Int,
          position : TwoDValue = TwoDValue(0.0, 0.0),
          velocity : TwoDValue = TwoDValue(0.0, 0.0)) {
    val attitude: TwoDAttitude = TwoDAttitude(position, velocity)

    var destination: TwoDValue? = null

    var nickname: String? = null

    /* Lab: displayName이 내재된 field 없이, nickname이 null이 아니면 nickname을, null이면 number에 해당하는 문자열을 나타내게 하시오. */
    val displayName: String
        get() = nickname ?: number   // 구현

    // Lab1: 만일 destinaion이 설정되지 않았다면, (0.0, 0.0)을 목적지로 간주할 것
    // Lab2: 만일 destinaion이 설정되지 않았다면, IllegalStateException을 던지도록 수정
    fun stepTowardDestination(duration: Double) {
        // Lab1
        destination = destination ?: TwoDValue(0.0, 0.0)

        // 목표점을 향한 방향 찾기
        // Lab: 함수내에 있는 destination뒤의 !!는 바람직하지 않다. 이를 바람직하게 바꾸시오
        // Lab2
        val dest = destination ?: throw IllegalStateException()
        val dx = dest.x - attitude.position.x
        val dy = dest.y - attitude.position.y
        val distance = sqrt(dx * dx + dy * dy)
        // 자동차의 방향을 목표점으로 돌리기
        attitude.direction = atan2(dy, dx)

        // 목표에 도달하기에 기간이 충분하다면 목표점에 도달한 뒤 이를 출력한다.
        if(duration * attitude.speed > distance) {
            println("$displayName arrived")
            attitude.setPosition( destination!!.x, destination!!.y)
        } else { // 기간이 충분하지 않다면, 필요한 만큼 진행한다.
            attitude.passTime(duration)
        }
    }
}

fun main() {
    val car = Car("12가1234", 2020)

    car.attitude.setPosition(1.0, 1.0)

    // Lab: 목적지 미설정 상태 -> 안전하게 탈출하여 다음 문장 (car.destination = TwoDValue(10.0, 0.0))을 수행하도록 만드시오
    try {
        car.stepTowardDestination(1.0)
        println("[${car.displayName}] pos=${car.attitude.position} vel=${car.attitude.velocity}")
    } catch (e: IllegalStateException) {
        println("No Destination")
    }

    // 목적지 설정 (nullable에 값 할당)
    car.destination = TwoDValue(10.0, 0.0)
    car.nickname = "KAU"

    // 확장 프로퍼티로 속도/방향 제어
    car.attitude.setVelocity(-3.0, -2.0)
    // 목적지로 3스텝 이동
    for(i in 1..3) {
        car.stepTowardDestination(1.0)
        println("[${car.displayName}] pos=${car.attitude.position} vel=${car.attitude.velocity}")
    }
}
