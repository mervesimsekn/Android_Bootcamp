package com.example.sayitahminoyunu

import java.util.Scanner
import kotlin.random.Random

fun main() {
    val randomSayi = Random.nextInt(0, 101)
    var tahminHakki = 3

    println("----------Sayı Tahmin Oyunu----------")
    println("-------------------------------------")

    println("Tahmin Hakkı: $tahminHakki")
    println("0-101 Arasında Tahmininizi Giriniz:")

    while (tahminHakki > 0) {

        val input = Scanner(System.`in`)
        val tahmin = input.nextInt()

        if (tahmin < randomSayi) {
            println("Daha Büyük Bir Sayı Deneyin!")
            tahminHakki--
            println("Kalan Tahmin Hakkı: $tahminHakki")
        } else if (tahmin > randomSayi) {
            println("Daha Küçük Bir Sayı Deneyin!")
            tahminHakki--
            println("Kalan Tahmin Hakkı $tahminHakki")
        } else {
            println("Tebrikler! Tahmininiz Doğru :)")
            break
        }

        if (tahminHakki == 0) {
            println("Tahmin Hakkınız Kalmadı")
            println("Doğru Sayı: $randomSayi")
        }

    }
}