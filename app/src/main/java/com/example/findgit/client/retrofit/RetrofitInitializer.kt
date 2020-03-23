package com.example.findgit.client.retrofit

import android.util.Log
import com.example.findgit.client.projeto.ProjetoRest
import com.example.findgit.service.BaseService
import com.example.findgit.util.DateFormatUtil
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {

    private val TAG = RetrofitInitializer::class.java.simpleName

    private val okHttp = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    private fun obtemAdpter(): Retrofit {

        val gsonConverter: Gson = GsonBuilder()
            .setDateFormat(DateFormatUtil.PADRAO_DH)
            .create()

        return Retrofit.Builder()
            .baseUrl(BaseService.URL_BASE_GITHUB)
            .addConverterFactory(GsonConverterFactory.create(gsonConverter))
            .client(okHttp)
            .build()
    }

    fun projetoService(): ProjetoRest? {

        return try {
            obtemAdpter().create(ProjetoRest::class.java)
        } catch (erro: Exception) {
            Log.e(TAG, "Ocorreu um erro ao fazer a pesquisa", erro)
            null
        }
    }
}