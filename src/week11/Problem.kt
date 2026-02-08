package week11

class Person(val firstName: String, val familyName: String, val age: Int)
data class Mailbox(val address: String, val person: Person)
fun main() {
    val box1 = Mailbox("Unknown", Person("John", "Doe", 25))
    val box2 = Mailbox("Unknown", Person("John", "Doe", 25))
    println(box1 == box2)
}