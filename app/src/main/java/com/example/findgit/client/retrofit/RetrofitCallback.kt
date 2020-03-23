package com.example.findgit.client.retrofit

import android.util.Log
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val TAG = "RetrofitCallback"

fun <T> callback(callResponse: (response: Response<T>?, throwable: Throwable?) -> Unit): Callback<T> {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            if (response?.isSuccessful!!) {
                callResponse(response, null)
            } else {
                try {
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    callResponse(null, Throwable(jObjError.getString("mensagem")))
                } catch (erro: Exception) {
                    Log.e(TAG, "Erro no callback", erro)
                    callResponse(null, Throwable(erro.message))
                }
            }
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            Log.e(TAG, "Requisição falhou", t as Exception)
            callResponse(null, t)
        }
    }

}