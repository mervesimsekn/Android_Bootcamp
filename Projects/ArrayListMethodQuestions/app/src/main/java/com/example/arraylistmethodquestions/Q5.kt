package com.example.arraylistmethodquestions

data class Workers(val name: String, var salary: Double)

fun main() {
    val workers = arrayListOf(
        Workers("Ahmet Yılmaz", 15000.0),
        Workers("Ayşe Kaya", 32000.0),
        Workers("Mehmet Demir", 29000.0),
        Workers("Fatma Şahin", 18500.0)
    )

    workers.forEach {
        it.salary += it.salary * 0.35
    }

    workers.shuffle()

    val siraliMaas = workers.sortedBy { it.salary }

    val enYuksek = workers.find { it -> it.salary == workers.maxOf { it.salary } }!!
    val enDusuk = workers.find { it -> it.salary == workers.minOf { it.salary } }!!

    println("En Yüksek Maaşı Alan Çalışan: ${enYuksek.name}, Maaşı: ${enYuksek.salary} ")
    println("En Düşük Maaşı Alan Çalışan: ${enDusuk.name}, Maaşı: ${enDusuk.salary}")

    val ortalamaMaas = workers.sumOf { it.salary } / workers.count()
    println("Ortalama Maaş: $ortalamaMaas")

}