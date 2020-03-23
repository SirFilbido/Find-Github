package com.example.findgit.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.findgit.client.BaseAsyncTask
import com.example.findgit.database.Database
import com.example.findgit.database.dao.ProjetoDAO
import com.example.findgit.form.ProjetoForm
import com.example.findgit.model.Projeto
import com.example.findgit.model.Usuario

class ProjetoRepository() {

    private val TAG = javaClass.simpleName
    private lateinit var context: Context

    var dao: ProjetoDAO? = null
    private lateinit var usuarioRepository: UsuarioRepository

    constructor(context: Context) : this() {
        this.context = context
        val database = Database.instance(context)
        dao = database.projetoDAO()
        usuarioRepository = UsuarioRepository(context)
    }

    fun salvar(projetoForm: ProjetoForm, callback: (Unit?) -> Unit) {
        try {

            usuarioRepository.salvar(Usuario(projetoForm.dono)) {

                val projeto = Projeto(projetoForm)

                BaseAsyncTask(executar = {
                    if (buscaPorId(projeto.id) == null) {
                        dao?.inserir(projeto)
                    } else {
                        dao?.atualizar(projeto)
                    }
                }, finaliza = callback)
                    .execute()
            }
        } catch (erro: Exception) {
            Log.e(TAG, "Ocorreu um erro ao salvar o projeto", erro)
        }
    }

    fun buscaPorId(idProjeto: Long): Projeto? {
        return try {
            dao?.buscaPorId(idProjeto)
        } catch (erro: Exception) {
            Log.e(TAG, "Ocorreu um erro ao buscar o projeto por id", erro)
            null
        }
    }

    fun buscaTodos(): MutableLiveData<List<ProjetoForm>> {

        val listaLiveData: MutableLiveData<List<ProjetoForm>> = MutableLiveData()
        val lista: MutableList<ProjetoForm> = mutableListOf()

        try {
            dao!!.buscaTodos().forEach {
                lista.add(ProjetoForm(it, usuarioRepository))
            }

            listaLiveData.value = lista

        } catch (erro: Exception) {
            Log.e(TAG, "Ocorreu um erro ao buscar todos os projetos", erro)
        }

        return listaLiveData
    }

}