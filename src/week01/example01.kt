package week01

/*

int main() {
    char name[5] = "Kotlin";
    printf("Hello, %s!\n", name);

    int i;
    for(i = 1; i <=5; i++) {
        printf("i = %d\n", i);
    }

    return 0;
}

 */

fun main() {
    val name = "Kotlin" // read-only
    println("Hello, $name!")    // 템플릿, syntactic sugar

    for (i in 1 .. 5) { // 기본적으로 step 1
        println("i = $i")
    }
}