package week03.OneD

class Car(val number: String,
          val makingYear: Int,
          position: Double = 0.0,
          velocity: Double = 0.0) {
    val attitude: OneDAttitude = OneDAttitude(position, velocity)
}
