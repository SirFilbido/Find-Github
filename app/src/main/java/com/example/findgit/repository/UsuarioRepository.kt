package com.example.findgit.repository

import android.content.Context
import android.util.Log
import com.example.findgit.client.BaseAsyncTask
import com.example.findgit.database.Database
import com.example.findgit.database.dao.UsuarioDAO
import com.example.findgit.model.Usuario

class UsuarioRepository() {

    private val TAG = javaClass.simpleName
    private lateinit var context: Context

    var dao: UsuarioDAO? = null

    constructor(context: Context) : this() {
        this.context = context
        val database = Database.instance(context)
        dao = database.usuarioDAO()
    }

    fun salvar(usuario: Usuario, callback: (Unit?) -> Unit) {
        try {
            BaseAsyncTask(executar = {
                if (buscaPorId(usuario.id) == null) {
                    dao?.inserir(usuario)
                } else {
                    dao?.atualizar(usuario)
                }
            }, finaliza = callback)
                .execute()
        } catch (erro: Exception) {
            Log.e(TAG, "Ocorreu um erro ao salvar o usuario", erro)
        }
    }

    fun buscaPorId(idUsuario: Long): Usuario? {
        return try {
            dao?.buscaPorId(idUsuario)
        } catch (erro: Exception) {
            Log.e(TAG, "Ocorreu um erro ao buscar o usuario por id", erro)
            null
        }
    }

}