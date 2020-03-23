package com.example.findgit.ui.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findgit.form.ProjetoForm
import com.example.findgit.repository.ProjetoRepository

class ProjetoViewModel(val context: Context) : ViewModel() {

    private val repositorio = ProjetoRepository(context)
    var listagemProjetos: MutableLiveData<List<ProjetoForm>> = MutableLiveData()

    fun buscarListagemProjetos() {
        this.listagemProjetos = repositorio.buscaTodos()
    }

    fun salvarProjeto(projetoForm: ProjetoForm, callback: () -> Unit) {
        repositorio.salvar(projetoForm) {
            callback()
        }
    }


}