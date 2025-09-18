package week01

/*

kotlin에서 모든 것은 객체이며, 자동으로 Heap에 저장된다.

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
    // 위의 내용처럼 모든 것을 객체 취급하기 때문에, 리터럴 값에 .toString(), .toInt()와 같은 메소드 사용 가능

    val name = 3.toString() // read-only
    val a: Int = 3 + 2.1.toInt()
    val age = name + 4.4;
    println("Hello, $name!")    // 템플릿, syntactic sugar

    for (i in 1 .. 5) { // 기본적으로 step 1
        println("i = $i")
    }

    // --------------------------

    /*

    자바와의 호환성을 위해 자바에서의 int array는 kotlin에선 객체로 인식 후 사용한다.

     */

    // Q : 그럼 자바로 구현한 메소드가 int arr를 리턴할 때 코틀린에서 자동으로 객체로 변환하기에 문제가 없나?
    // 박싱의 오버헤드 줄이기 위해서 ?

    val arr = arrayOf(1, 2, 3, 4)
    val iArr = intArrayOf(1, 2, 3, 4)
    val name1 = iArr[2].toString()
    val arr2 = arr.copyOfRange(2, arr.size)

    if(arr == iArr) {
        println("Yes")
    } else {
        println("No")
    }
}