package week07

// Product 클래스 정의
class Product(var name: String = "", var price: Int = 0) {
    override fun toString(): String {
        return "Product(name='$name', price=$price)"
    }
}

// TO DO 1: Product의 확장함수 discounted 만들기
// 함수: discounted(rate: Double): Int 형식
// rate만큼 할인할 때 가격을 반환
fun Product.discounted(rate: Double?): Int {
    val new_rate = rate ?: 0.0
    return (this.price * (1 - new_rate)).toInt()
}

// TO DO 2: 문자열이 null이 아니면 첫 글자를 대문자로 바꿔주는 확장함수 만들기
// 함수: capitalizeIfNotNull()
// null이면 그냥 null을 반환하도록 String관련하여 확장
fun String?.capitalizeIfNotNull() : String? {
    if (this.isNullOrEmpty()) {
        return this
    }
    return this[0].uppercase() + this.substring(1)
}

fun main() {
    // 다음 코드를 영역함수 apply를 이용하여 수정
    val product = Product().apply {
        name = "wireless mouse"
        price = 15000
    }.also {
        // 다음을 영역함수 also를 이용하여 출력
        println("상품 객체 생성 완료: $it")
    }

    // 다음 코드를 product에 대한 영역함수 run을 이용하여 수정
    // run: 연산 결과 반환
    val discountedPrice = product.run {
        // this 키워드로 product 객체에 접근하여 discounted 함수를 호출합니다.
        this.discounted(0.1)
    }
    println("할인된 가격: ${discountedPrice}원")


    val buyerName: String? = "cheolgi"
    // null이 아닐 때만 let으로 처리하는 형식으로 수정
    buyerName?.let {
        println("구매자 이름(대문자): ${it.capitalizeIfNotNull()}")
    }

    val nullBuyer: String? = null
    // null이 아닐 때만 let으로 처리하는 형식으로 위와 같이...
    nullBuyer?.let {
        println("구매자 이름(대문자): ${it.capitalizeIfNotNull()}")
    } ?: println("구매자 명이 없습니다.")
}
