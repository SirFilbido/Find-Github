package com.example.findgit.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.findgit.R
import kotlin.random.Random

class RandomUtil {

    companion object {

        private val context = ApplicationProvider.getApplicationContext<Context>()

        private val caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
        private val numeros = "0123456789"
        private val alfanumerico =
            "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789012345678901234567890123456789"
        private val listaNomes =
            context.getString(R.string.listaNomes).split(",").toTypedArray()
        private val listaLugares =
            context.getString(R.string.listaLugares).split(",").toTypedArray()
        private val loremIpsum =
            context.getString(R.string.loremIpsum).split(",").toTypedArray()

        fun geraStringRandom(tamanho: Int): String {
            return (1..tamanho)
                .map { caracteres.random() }
                .joinToString("")
        }

        fun geraNumeroRandom(decimal: Int): Long {
            return (1..decimal)
                .map { numeros.random() }
                .joinToString("")
                .toLong()
        }

        fun geraAlfanumericoRandom(tamanho: Int): String {
            return (1..tamanho)
                .map { alfanumerico.random() }
                .joinToString("")
        }

        fun geraNomeRandom(): String {
            return listaNomes.random()
        }

        fun geraListaNomeRandom(tamanho: Int): String {
            return (1..tamanho)
                .map { listaNomes.random() }
                .joinToString(", ")
        }

        fun geraLugarRandom(): String {
            return listaLugares.random()
        }

        fun geraListaLugaresRandom(tamanho: Int): String {
            return (1..tamanho)
                .map { listaLugares.random() }
                .joinToString(", ")
        }

        fun geraNumeroIntervalo(minimo: Long, maximo: Long): Long {
            return Random.nextLong(maximo - minimo + 1) + minimo
        }

        fun geraDecimalIntervalo(minimo: Long, maximo: Long): Double {
            val number =
                Random.nextDouble(maximo.toDouble() - minimo.toDouble() + 1.0) + minimo.toDouble()
            return Math.round(number * 100.0) / 100.0
        }

        fun geraTextoRandom(quantPalavras: Int): String {
            return (1..quantPalavras)
                .map { loremIpsum.random() }
                .joinToString(" ")
        }

        fun geraBooleanRandom(): Boolean {
            return Random.nextBoolean()
        }
    }

}