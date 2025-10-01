package week03.TwoD

import kotlin.math.*

class TwoDAttitude(var position: TwoDValue,
                   var velocity: TwoDValue) {

    fun passTime(time: Double) {
//        position.x += velocity.x * time
//        position.y += velocity.y * time
        val newX = position.x + velocity.x * time
        val newY = position.y + velocity.y * time
        position = TwoDValue(newX, newY)
    }

    fun setDirectionAndSpeed(theta: Double,
                             speed: Double) {
//        velocity.x = speed * cos(theta)
//        velocity.y = speed * sin(theta)
        val newX = speed * cos(theta)
        val newY = speed * sin(theta)
        velocity = TwoDValue(newX, newY)
    }

    fun setPosition(x: Double,
                    y: Double) {
        position = TwoDValue(x, y)
    }

    fun setVelocity(x: Double,
                    y: Double) {
        velocity = TwoDValue(x, y)
    }
}
