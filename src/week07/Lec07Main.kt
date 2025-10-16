package week07

/*

    확장 멤버
    영역 함수
        - run   :
        - let   :
        - apply : 초기화 루틴, 주어가 되는 것이기에 람다함수 내에서 객체를 this로 처리

 */
class Circle() {
    var radius: Double = 0.0
    var x: Double = 0.0
    var y: Double = 0.0

    val area: Double
        get() = radius * radius * Math.PI
}


fun inchToCm1(length: Double): Double = length * 2.54

// 확장 함수
fun Double.inchToCm(): Double = this * 2.54

// 확장 프로퍼티
val Double.million : Double // 어짜피 val이라 read-only 특성이 있기에 setter는 설정할 필요 없음
    get() = (this * 1_000_000.0)

fun main() {
    // 확장 함수 예시
    println(10.0.inchToCm())

    // 확장 프로퍼티 예시
    println(2.5.million)

    // 영역 함수
    /*
    val circle = Circle()
    circle.radius = 5.0
    circle.x = 10.0
    circle.y = 15.0

    or

    fun initCircle(c: Circle) {
        c.radius = 5.0
        c.x = 10.0
        c.y = 15.0
    }
    으로 쓰면 너무 불편하고, 코드가 길어지며, 유지보수하기 불편함
    apply를 써서 의미 명확하게 두면 읽기도 편하고 좋음
     */

    // 대입 느낌으로 쓰면 apply
    val circle = Circle().apply {
        radius = 5.0
        x = 10.0
        y = 15.0
    }

    // 일회성 확장함수 느낌으로 쓰면 run
    val newArea = circle.run {
        radius = 10.0
        x = -10.0
        y = -15.0
        area    // run에서는 마지막 문장이 반환 구문
    }
    println("newArea = $newArea")

    var circle1: Circle? = Circle().apply {
        radius = 10.0
        x = 10.0
        y = 15.0
    }

    // null-check를 위해 safety call을 사용하지 않고 let을 사용
    circle1?.let {
        // 주요작업
        it.radius = 5.0
        it.x = 10.0
        it.y = -15.0
        println("주요작업 New Area is ${it.area}")   // 혹은 println이 부가적인 작업이라 느껴지면
    }.also {
        // 보조작업
        println("부가적인 작업")  // 이런식으로 also를 사용해서 주요작업, 부요작업을 나누는 형식을 구현할 수 있다.
    }
}