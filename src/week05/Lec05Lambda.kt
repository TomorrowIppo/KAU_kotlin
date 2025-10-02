package week05

fun repeatWithIndex(times: Int, action: (Int) -> Unit ) {
    for(idx in 1..times) {
        action(idx)
    }
}

fun main() {
    repeatWithIndex(5) {
        println("Custom Lambda Example $it")
    }

    repeat(5, {println("Hello World")})
}