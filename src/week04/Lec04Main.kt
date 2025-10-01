package week04

import kotlin.math.PI
import kotlin.math.sqrt

// 이따구로 코드짜면 setter끼리 왔다갔다 무한반복
//class Circle(var x: Double, var y: Double) {
//    var radius: Double? = null
//        set(value) {
//            field = value
//            val r = value
//            if (r != null) {
//                area = r * r * PI
//            } else {
//                area = 0.0
//            }
//        }
//    var area: Double? = null  // backing field
//        set(value) {
//            field = value
//            val a = value
//            if (a != null) {
//                radius = sqrt(value / PI)
//            } else radius = null
//
////            if (value == null) { radius = null }
////            else radius = sqrt(value / PI)
//        }
//}

// 두 개의 필드가 서로를 바꾸는 것을 설정한다면 무한 재귀를 제거해야한다
class Circle(var x: Double, var y: Double) {
    var radius: Double? = null
        set(value) {
            field = value
            val r = value
            if (r != null) {
                area = r * r * PI
            } else {
                area = 0.0
            }
        }
    var area: Double? = null  // backing field
        set(value) {
            val r = radius ?: 0.0
            if (radius == null && area != null) return
            if (area == r * r * PI) return
            val a = value
            field = value
            if (a != null) {
                radius = sqrt(value / PI)
            } else radius = null
        }
}

fun main() {
    val circle1 = Circle(9.0, 9.0)
    circle1.radius = 9.0
}