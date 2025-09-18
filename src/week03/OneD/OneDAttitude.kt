package week03.OneD

class OneDAttitude(var position: Double,
                   var velocity: Double) {
    fun passTime(time: Double) {
        position += velocity * time
    }
}