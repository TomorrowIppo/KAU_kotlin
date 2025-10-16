import kotlin.math.sqrt

class Circle(val x: Double, val y: Double, val radius: Double) {
    init {
        // To Do: Radius가 0보자 작다면 IllegalArgumentException을 던지시오
        if (radius < 0.0) throw IllegalArgumentException("radius must be positive.")

        // To Do: Companion object의 count를 1 증가
        count++
    }

    fun overlaps(other: Circle): Boolean {
        val dx = x - other.x;
        val dy = y - other.y;
        val distance = sqrt(dx * dx + dy * dy);
        if (distance < radius + other.radius) {
            // To do: overlapCallback을 호출
            val overlapAmount = (radius + other.radius) - distance
            overlapCallback?.invoke(overlapAmount)

            return true;
        }

        return false;
    }

    companion object {
        // To do: Circle 클래스 밖에서는 값을 바꿀 수 없도록
        var count = 0
            private set

        // To do: 겹치는 반지름의 길이를 Double 인자로 받고 반환 값이 없는 람다함수 변수 정의
        var overlapCallback: ((overlap : Double) -> Unit)? = null
    }
}

fun main() {
    val circle1 = Circle(1.0, 1.0, 2.0)
    val circle2 = Circle(0.0, -3.0, 2.0)
    val circle3 = Circle(2.0, 2.0, 2.0)

    // To do: "XX만큼 겹쳤어요." 라는 문자열이 출력되는 람다함수 대입
    Circle.overlapCallback = {
        println("${it}만큼 겹쳤어요.")
    }

    println("만든 총 원의 갯수: ${Circle.count}")

    circle1.overlaps(circle2)
    circle2.overlaps(circle3)
    circle1.overlaps(circle3)
}