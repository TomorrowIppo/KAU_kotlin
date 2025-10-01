package week03.TwoD

class Car(
    val number: String,
    val makingYear: Int,
    var attitude: TwoDAttitude = TwoDAttitude(TwoDValue(0.0, 0.0), TwoDValue(0.0, 0.0))
)
