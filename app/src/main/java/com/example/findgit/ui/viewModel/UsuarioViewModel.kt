package com.example.findgit.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.findgit.model.Projeto
import com.example.findgit.repository.UsuarioRepository

class UsuarioViewModel(val context: Context) : ViewModel() {

    private val repositorio = UsuarioRepository(context)

    fun buscaAvatarDono(projeto: Projeto): String {
        TODO("Not yet implemented")
    }

    fun buscaNomeDono(projeto: Projeto): String {
        TODO("Not yet implemented")
    }
}