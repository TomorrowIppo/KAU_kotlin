package week03.TwoD

import kotlin.math.*

class TwoDAttitude(var position: TwoDValue,
                   var velocity: TwoDValue) {

    fun passTime(time: Double) {
        position.x += velocity.x * time
        position.y += velocity.y * time
    }

    fun setDirectionAndSpeed(theta: Double,
                             speed: Double) {
        position.x = speed * cos(theta)
        position.y = speed * sin(theta)
    }

    fun setPosition(x: Double,
                    y: Double) {
        position.x = x
        position.y = y
    }

    fun setVelocity(x: Double,
                    y: Double) {
        velocity.x = x
        velocity.y = y
    }
}
