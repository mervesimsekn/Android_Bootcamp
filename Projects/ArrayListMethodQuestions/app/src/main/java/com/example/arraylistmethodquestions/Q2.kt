package com.example.arraylistmethodquestions

fun main() {
    val names = arrayListOf("Sinem", "Ali", "Hümeyra", "Cem", "Gizem")

    names.sort()

    println("Bir İsim Giriniz:")
    val input = readLine()

    if (names.contains(input)) {
        println("Aradığınız İsim Listede Yer Almaktadır.")
        println(input?.reversed()?.uppercase())
        println(names)
    } else {
        println("Aradığınız İsim Listede Yer Almamaktadır.")
    }

}