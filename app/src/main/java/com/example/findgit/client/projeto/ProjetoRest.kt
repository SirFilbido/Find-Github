package com.example.findgit.client.projeto

import com.example.findgit.holder.BuscaHolder
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProjetoRest {

    @GET("/search/repositories")
    fun buscaProjetosPorPagina(
        @Query("q") termo: String?,
        @Query("per_page") porPagina: Int?,
        @Query("page") nPagina: Int?
    ): Call<BuscaHolder>?
}