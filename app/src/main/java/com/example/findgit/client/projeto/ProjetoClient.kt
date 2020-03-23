package com.example.findgit.client.projeto

import com.example.findgit.client.retrofit.RetrofitInitializer
import com.example.findgit.client.retrofit.callback
import com.example.findgit.holder.BuscaHolder

class ProjetoClient {

    companion object {

        private val TAG = ProjetoClient::class.java.simpleName

        fun buscaProjetosPorPagina(
            termo: String,
            nPagina: Int,
            sucesso: (holder: BuscaHolder) -> Unit,
            falha: (throwable: Throwable) -> Unit
        ) {

            val call =
                RetrofitInitializer().projetoService()
                    ?.buscaProjetosPorPagina(termo, 10, nPagina)

            call?.enqueue(callback { response, thorwable ->
                response?.let {
                    sucesso(it.body()!!)
                }
                thorwable?.let {
                    falha(it)
                }
            })
        }
    }
}