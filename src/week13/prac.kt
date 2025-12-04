package week13

/**
 * 과제: 수강생 성적 및 통계 관리 프로그램
 *
 * 아래 지시사항(// TODO:)을 따라 하나의 프로그램을 완성하시오.
 * main()에서 step1 ~ step6을 순서대로 호출하여 전체 흐름을 확인할 수 있도록 작성한다.
 *
 * 사용해야 하는 주요 개념:
 * - Array, IntArray, ArrayList / MutableList
 * - Set / MutableSet
 * - Map / MutableMap
 * - filter, all, any, none, sorted, sortedDescending, count, etc.
 * - Comparable, TreeSet
 */

fun main() {
    val scoresList = step1_initScores()

    println("===== [1단계] 점수 리스트 생성 완료 =====")
    println("scoresList = $scoresList")
    println()

    val namesSet = step2_handleNames()
    println("===== [2단계] 이름 Set 처리 완료 =====")
    println("namesSet = $namesSet")
    println()

    val scoreMap = step3_buildScoreMap(scoresList)
    println("===== [3단계] 학번-점수 Map 구성 완료 =====")
    println("scoreMap = $scoreMap")
    println()

    step4_passFail(scoreMap)
    println("===== [4단계] 합격/불합격 판정 완료 =====")
    println()

    step5_sortAndAggregate(scoreMap)
    println("===== [5단계] 점수 정렬 및 집계 완료 =====")
    println()

    step6_studentsTreeSet(scoreMap)
    println("===== [6단계] Student + TreeSet 처리 완료 =====")
    println()
}

// -------------------------------------------------------------------------------------------------

/* -------------------------------------------------------------
 * [1단계] 초기 점수 배열(IntArray) & ArrayList로 변환
 *
 * 지시사항:
 * 1) 아래와 같은 초기 점수 배열을 IntArray로 선언한다.
 * val scores = intArrayOf(95, 70, 82, 70, 60, 88, 100)
 * 2) 이 배열을 MutableList<Int> (예: ArrayList 또는 mutableListOf) 로 변환한다.
 * 3) 변환한 리스트에 새 점수 77, 85 를 추가한다.
 * 4) 모든 원소를 println으로 한 줄씩 출력해 본다.
 * 5) 최종적으로 만들어진 MutableList<Int>를 반환한다.
 * ------------------------------------------------------------- */
fun step1_initScores(): MutableList<Int> {
    val scores = intArrayOf(95, 70, 82, 70, 60, 88, 100)

   val scoresList: MutableList<Int> = scores.toMutableList()

    scoresList.add(77)
    scoresList.add(85)

    println("[1단계] 모든 원소를 한 줄씩 출력:")
    scoresList.forEach { println(it) }

    return scoresList
}

/* -------------------------------------------------------------
 * [2단계] 이름 Set으로 중복 제거
 *
 * 지시사항:
 * 1) 아래 이름들을 가지고 MutableSet<String>을 만든다.
 * "Kim", "Lee", "Park", "Choi", "Kim", "Lee"
 * 2) Set의 크기와 원소들을 출력하여 중복이 제거되었는지 확인한다.
 * 3) 새로운 이름 "Jung" 을 추가하고, 다시 전체 Set을 출력한다.
 * 4) 최종 MutableSet<String>을 반환한다.
 * ------------------------------------------------------------- */
fun step2_handleNames(): MutableSet<String> {
    val namesSet: MutableSet<String> = mutableSetOf("Kim", "Lee", "Park", "Choi", "Kim", "Lee")

    println("[2단계] 초기 Set (중복 제거 확인):")
    println("크기: ${namesSet.size}") // 4가 출력되어야 함
    println("원소: $namesSet")

    namesSet.add("Jung")
    println("[2단계] 'Jung' 추가 후 Set:")
    println("원소: $namesSet")

    return namesSet
}

/* -------------------------------------------------------------
 * [3단계] 학번–점수 Map 구성 및 조회 유틸리티
 *
 * 지시사항:
 * 1) 한 과목의 수강생을 학번(Int) -> 점수(Int) 로 관리하는 MutableMap<Int, Int> 생성.
 * - 예시(자유롭게 수정 가능하나 최소 4명 이상):
 * 1001 -> 95
 * 1002 -> 70
 * 1003 -> 82
 * 1004 -> 60
 *
 * 힌트: mutableMapOf(1001 to 95, ...)
 *
 * 2) 학번 1005의 점수를 조회하되, 없으면 "해당 학번 없음" 이라는
 * 메시지를 println 하도록 코드를 작성한다.
 * - map.getOrDefault(1005, ???)
 * - 또는 map[1005] ?: ???  방식 활용 가능
 *
 * 3) Map의 keys, values, entries를 각각 출력한다.
 *
 * 4) 학번 1004의 점수를 75로 수정하고, 수정 후 Map 전체를 다시 출력한다.
 *
 * 5) 최종 MutableMap<Int, Int>를 반환한다.
 * ------------------------------------------------------------- */
fun step3_buildScoreMap(scoresList: List<Int>): MutableMap<Int, Int> {
    val scoreMap: MutableMap<Int, Int> = mutableMapOf(
        1001 to 95,
        1002 to 70,
        1003 to 82,
        1004 to 60,
        1005 to 88,
        1006 to 100,
        1007 to 77,
        1008 to 85
    )

    println("[3단계] 학번 1009의 점수 조회:")
    val score1009 = scoreMap.getOrDefault(1009, -1) // -1을 기본값으로 사용
    if (score1009 == -1) {
        println("학번 1009: 해당 학번 없음")
    } else {
        println("학번 1009 점수: $score1009")
    }

    println("[3단계] 학번 1002의 점수 조회 (Elvis): ${scoreMap[1002] ?: "해당 학번 없음"}")

    println("[3단계] Map의 keys (학번): ${scoreMap.keys}")
    println("[3단계] Map의 values (점수): ${scoreMap.values}")
    println("[3단계] Map의 entries (학번=점수): ${scoreMap.entries}")

    scoreMap[1004] = 75
    println("[3단계] 학번 1004 점수 수정 후 Map 전체:")
    println(scoreMap)

    // 5) Map 반환
    return scoreMap
}

/* -------------------------------------------------------------
 * [4단계] Map + filter + all/any/none 으로 합격자 판정
 *
 * 지시사항:
 * 1) 합격 기준을 70점 이상으로 정한다 (변수로 선언).
 *
 * 2) 3단계의 scoreMap을 기준으로:
 * - 점수가 70 이상인 학번만 모아서 합격자 리스트(List<Int>)를 만든다.
 * -> map.filter { it.value >= 기준 }.keys 또는 map.filter { ... }.map { it.key }
 *
 * - 점수가 70 미만인 학번만 모아서 불합격자 리스트(List<Int>)를 만든다.
 *
 * 그리고 두 리스트를 각각 println으로 출력한다.
 *
 * 3) all, any, none을 이용해 아래 조건을 검사하고 출력한다.
 * - 모든 수강생이 50점 이상인가?  (all)
 * - 한 명이라도 90점 이상인가?  (any)
 * - 점수가 0점인 학생이 아무도 없는가? (none)
 * ------------------------------------------------------------- */
fun step4_passFail(scoreMap: Map<Int, Int>) {
    val passScore = 70

    val passStudents = scoreMap
        .filter { it.value >= passScore }
        .keys
        .toList()

    val failStudents = scoreMap
        .filter { it.value < passScore }
        .keys
        .toList()

    println("[4단계] 합격자 리스트 (점수 $passScore 이상): $passStudents")
    println("[4단계] 불합격자 리스트 (점수 $passScore 미만): $failStudents")

    val scores = scoreMap.values

    val allOver50 = scores.all { it >= 50 }
    println("[4단계] 모든 수강생이 50점 이상인가? (all): $allOver50") // true

    val anyOver90 = scores.any { it >= 90 }
    println("[4단계] 한 명이라도 90점 이상인가? (any): $anyOver90") // true (95, 100점 있음)

    val noneIsZero = scores.none { it == 0 }
    println("[4단계] 점수가 0점인 학생이 아무도 없는가? (none): $noneIsZero") // true
}

/* -------------------------------------------------------------
 * [5단계] 점수 정렬 및 집계(reduce / fold)
 *
 * 지시사항:
 * 1) scoreMap의 values를 이용하여 점수 리스트(List<Int>)를 만든다.
 *
 * 2) 이 리스트를 이용해:
 * - 오름차순 정렬된 새로운 리스트(sorted)를 만든 후 출력
 * - 내림차순 정렬된 새로운 리스트(sortedDescending)를 만든 후 출력
 *
 * 3) 내림차순 기준 상위 3명 점수만 리스트로 잘라서(예: take(3)) 출력한다.
 *
 * 4) reduce 또는 fold를 이용해 전체 점수의 "총합"을 구한다.
 * - val sum = scores.reduce { acc, n -> ... }
 * - 또는 fold(0) { acc, n -> ... }
 *
 * 5) 평균 점수를 계산해서 출력한다.
 * ------------------------------------------------------------- */
fun step5_sortAndAggregate(scoreMap: Map<Int, Int>) {
    val scores = scoreMap.values.toList()
    println("[5단계] 원본 점수 리스트: $scores")

    val sortedScores = scores.sorted()
    println("[5단계] 오름차순 정렬: $sortedScores")

    val sortedScoresDesc = scores.sortedDescending()
    println("[5단계] 내림차순 정렬: $sortedScoresDesc")

    val top3Scores = sortedScoresDesc.take(3)
    println("[5단계] 상위 3명 점수 (내림차순): $top3Scores")

    val sum = scores.fold(0) { acc, score ->
        acc + score
    }

    println("[5단계] 전체 점수 총합 (fold): $sum")


    val average = sum.toDouble() / scores.size
    println("[5단계] 평균 점수: ${String.format("%.2f", average)}") // 소수점 둘째 자리까지 출력
}

/* -------------------------------------------------------------
 * [6단계] 수강생 클래스 + TreeSet + Comparable
 *
 * 지시사항:
 * 1) Student 클래스를 정의한다. (아래에 구현되어 있음)
 * 2) 3단계에서 만든 scoreMap을 활용하거나, 별도로 데이터를 만들어서
 * 여러 Student 객체를 생성한다.
 * 3) Student들을 TreeSet<Student>에 넣는다.
 * 4) TreeSet을 그대로 println 하여 이름 기준으로 정렬되어 있는지 확인한다.
 * 5) score가 80점 이상인 학생만 filter하여 새로운 리스트로 만들고 출력한다.
 * ------------------------------------------------------------- */
fun step6_studentsTreeSet(scoreMap: Map<Int, Int>) {
    val studentData = listOf(
        Triple(1001, "Kim", 95),
        Triple(1002, "Lee", 70),
        Triple(1003, "Park", 82),
        Triple(1004, "Choi", 75),
        Triple(1005, "Lee", 88), // 이름 중복 확인용 (Lee)
        Triple(1006, "Kim", 100) // 이름 중복 확인용 (Kim)
    )

    val studentList = studentData.map { (id, name, score) ->
        Student(id, name, score)
    }

    val studentTreeSet = java.util.TreeSet<Student>()
    studentTreeSet.addAll(studentList)

    println("[6단계] TreeSet 정렬 결과 (이름 오름차순, 학번 오름차순):")
    println(studentTreeSet)

    val highScoreStudents = studentTreeSet
        .filter { it.score >= 80 }
        .toList()

    println("[6단계] 점수 80점 이상 학생 (filter):")
    println(highScoreStudents)
}

/**
 * Student 클래스
 *
 * 지시사항:
 * - id, name, score 3개의 프로퍼티를 가진다.
 * - Comparable<Student>를 구현한다.
 * - compareTo(other: Student) 에서:
 * 1) name 기준 오름차순 정렬
 * 2) name이 같다면 id 기준 오름차순 정렬
 * - toString()을 오버라이드해서 "(id, name, score)" 형태의 문자열을 반환하게 한다.
 */
class Student(
    val id: Int,
    val name: String,
    val score: Int
) : Comparable<Student> {

    override fun compareTo(other: Student): Int {
        val nameComparison = this.name.compareTo(other.name)

        return if (nameComparison != 0) {
            nameComparison
        } else {
            this.id.compareTo(other.id)
        }
    }

    override fun toString(): String {
        return "($id, $name, $score)"
    }
}