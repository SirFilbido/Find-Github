package com.example.findgit.service

import android.util.Log
import com.example.findgit.client.projeto.ProjetoClient
import com.example.findgit.form.ProjetoForm
import com.example.findgit.holder.ProjetoHolder
import com.example.findgit.model.Projeto

class ProjetoService : BaseService() {

    companion object {

        private val TAG = ProjetoService::class.java.simpleName

        fun buscaProjetosPorTermo(
            termo: String, pagina: Int,
            sucesso: (lista: MutableList<ProjetoForm>) -> Unit,
            falha: (throwable: Throwable) -> Unit
        ) {
            Log.w(TAG, "Inicio - Busca por termo $termo e pagina $pagina")

            try {
                val lista: MutableList<ProjetoForm> = ArrayList()

                ProjetoClient.buscaProjetosPorPagina(termo, pagina, {

                    it.items.forEach {
                        lista.add(ProjetoForm(it))
                    }

                    sucesso(lista)
                }, {
                })
            } catch (erro: Exception) {
                Log.e(TAG, "Ocorreu um erro ao fazer a busca", erro)
                falha(erro)
            }
        }
    }
}