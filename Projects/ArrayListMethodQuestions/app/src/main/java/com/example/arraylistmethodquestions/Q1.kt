package com.example.arraylistmethodquestions

fun main() {

    val isimler = arrayListOf("Merve", "Alp", "Aslı", "İpek", "Ali")

    println("Aralarına Virgül Koyarak 3 Adet İsim Giriniz:")
    val yeniIsimler = readLine()

    val isimlerList = yeniIsimler?.split(",")?.map { it.trim() }

    if (isimlerList != null) {
        isimler.addAll(isimlerList)
    }
    println("İsimler: $isimler")

}