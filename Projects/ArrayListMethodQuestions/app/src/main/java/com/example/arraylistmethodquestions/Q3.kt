package com.example.arraylistmethodquestions

data class Student(val name: String, val age: Int, val school: String)

fun main() {

    val students = arrayListOf(
        Student("Ahmet", 20, "Üniversite A"),
        Student("Ayşe", 27, "Üniversite B"),
        Student("Mehmet", 22, "Üniversite C"),
        Student("Fatma", 28, "Üniversite A"),
        Student("Ali", 29, "Üniversite B"),
        Student("Feyza", 24, "Üniversite A"),
        Student("Berkay", 22, "Üniversite B"),
        Student("Caner", 26, "Üniversite A")
    )

    val enKucuk = students.find { it.age == students.minOf { student -> student.age } }!!
    val enBuyuk = students.find { it.age == students.maxOf { student -> student.age } }!!

    val maxIndex =
        students.indexOf(Student(enBuyuk.name, students.maxOf { it.age }, enBuyuk.school))
    val minIndex =
        students.indexOf(Student(enKucuk.name, students.minOf { it.age }, enKucuk.school))

    println("En Küçük Öğrencinin Adı: ${enKucuk.name}, Listedeki İndeksi:$minIndex ")
    println("En Büyük Öğrencinin Adı: ${enBuyuk.name}, Listedeki İndeksi: $maxIndex")

    val universiteA = students.filter { it.school == "Üniversite A" }
    val yirmiBesAlti = universiteA.filter { it.age < 25 }
    val yirmiBesUstu = universiteA.filter { it.age > 25 }


    println("25 Yaşından Küçük Olanlar: ")
    for (i in yirmiBesAlti) {

        println("Ad: ${i.name}, Yaş: ${i.age}, Üniversite: ${i.school}")
    }

    println("25 Yaşından Büyük Olanlar: ")
    for (i in yirmiBesUstu) {
        println("Ad: ${i.name}, Yaş: ${i.age}, Üniversite: ${i.school}")
    }

}