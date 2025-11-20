package week12

// Status라는 enum class를 만드시오.
// enum class의 생성자는 label이라는 문자열을 파라미터로 받아 이를 불변 프로퍼티로 삼는다.
// Status는 다음의 5가지 상태를 가진다
// PENDING (label: "주문 접수")
// PREPARING (label: "상품 준비 중"),
// SHIPPED (label: "배송 중"),
// DELIVERED (label: "배송 완료"),
// CANCELLED (label: "주문 취소");


// Order라는 data class를 최대한 간단하게 만드시오
// 형태는 main문 안을 참고하시오.


// OrderResult라는 sealed class를 만드시오
// OrderResult는 Success와 Failure라는 하위 클래스를 가짐
// Success와 Failure의 생성자 및 프로퍼티는 main 내부와 processOrder 함수를 참고

enum class Status(val label: String) {
    PENDING("주문 접수"),
    PREPARING("상품 준비 중"),
    SHIPPED("배송 중"),
    DELIVERED("배송 완료"),
    CANCELLED("주문 취소");
}

data class Order(
    val orderId: Int, // 주문 ID
    val customer: String, // 고객 이름
    val amount: Int, // 주문 금액
    val status: Status // 현재 주문 상태
)

sealed class OrderResult {
    data class Success(val message: String) : OrderResult()

    data class Failure(val reason: String) : OrderResult()
}

fun processOrder(order: Order): OrderResult {
    return when {
        // 주문 금액이 0 이하인 경우
        order.amount <= 0 ->
            OrderResult.Failure("Invalid amount: Amount must be positive")

        // 이미 취소된 주문인 경우
        order.status == Status.CANCELLED ->
            OrderResult.Failure("Order already cancelled")

        // 이미 배송 완료된 주문인 경우
        order.status == Status.DELIVERED ->
            OrderResult.Failure("Order already delivered")

        // 위의 모든 조건을 통과하고 주문 처리가 성공한 경우
        else ->
            OrderResult.Success("Order ${order.orderId} (Customer: ${order.customer}) in status ${order.status.label}")
    }
}

fun main() {
    val n = readLine()?.toIntOrNull() ?: return

    val orders = mutableListOf<Order>()

    repeat(n) {
        val line = readLine() ?: return
        val tokens = line.split(" ")
        if (tokens.size < 4) return

        val orderId = tokens[0].toInt()
        val customer = tokens[1]
        val amount = tokens[2].toInt()
        val status = Status.valueOf(tokens[3]) // PENDING, PREPARING 등

        val order = Order(orderId, customer, amount, status)
        orders.add(order)
    }

    // 주문 처리 결과 출력
    for (order in orders) {
        when (val result = processOrder(order)) {
            is OrderResult.Success ->
                println("SUCCESS: ${result.message}")

            is OrderResult.Failure ->
                println("FAILURE: ${result.reason}")
        }
    }
}