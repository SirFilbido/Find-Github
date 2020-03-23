package com.example.findgit.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class DateFormatUtil {

    companion object {
        private val TAG = "DateFormatUtil"

        const val PADRAO_DH: String = "dd/MM/yyyy HH:mm:ss"
        const val PADRAO_RETROFIT: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        const val PADRAO_DH_TELA: String = "dd/MM/yyyy HH:mm"
        const val PADRAO_DATA: String = "dd/MM/yyyy"

        val FORMATO_DH_TELA: SimpleDateFormat = SimpleDateFormat(PADRAO_DH_TELA, Locale.ENGLISH)
        val FORMATO_DATA: SimpleDateFormat = SimpleDateFormat(PADRAO_DATA, Locale.ENGLISH)
        val FORMATO_RETROFIT: SimpleDateFormat = SimpleDateFormat(PADRAO_RETROFIT, Locale.ENGLISH)

        /**
         * Formata uma String para um Calendar
         */
        fun stringToCalendar(data: String, formato: SimpleDateFormat): Calendar {

            val calendar = Calendar.getInstance()

            try {
                calendar.time = formato.parse(data)!!
            } catch (erro: Exception) {
                Log.e(TAG, "Erro ao converter o string para calendar", erro)
            }

            return calendar

        }

        /**
         * Formata um Calendar para String no formato desejado
         */
        fun formatar(data: Calendar, formato: SimpleDateFormat): String? {
            return try {
                formato.format(data.time)
            } catch (erro: Exception) {
                Log.e(TAG, "Erro ao converter o calendar para string", erro)
                null
            }
        }

    }

}