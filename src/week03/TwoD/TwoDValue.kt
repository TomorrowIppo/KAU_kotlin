package week03.TwoD

// TwoDValue.kt
//class TwoDValue(
//    val x: Double,
//    val y: Double
//)

// 아래 코드는 성능은 빨라지더라도 위험할 수 있음
class TwoDValue(x: Double, y: Double) {
    var x: Double = x
        internal set
    var y: Double = y
        internal set
}
