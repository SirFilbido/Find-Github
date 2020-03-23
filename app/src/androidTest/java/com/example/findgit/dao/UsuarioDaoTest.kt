package com.example.findgit.dao

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.findgit.database.AppDatabase
import com.example.findgit.database.Database
import com.example.findgit.database.dao.UsuarioDAO
import com.example.findgit.model.UsuarioTest
import com.example.findgit.util.RandomUtil
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsuarioDaoTest {

    private val TAG = javaClass.simpleName
    private var database: AppDatabase? = null
    val context = ApplicationProvider.getApplicationContext<Context>()

    private lateinit var usuarioDao: UsuarioDAO

    @Before
    fun antesDeExecutar() {
        Log.w(TAG, "@Before - Antes do teste - Inicio")
        database = Database.instance(context)
        usuarioDao = database?.usuarioDAO()!!

        limpaBaseDeTestes()

        Log.w(TAG, "@Before - Antes do teste - Fim")
    }

    @After
    fun aposExecutar() {
        Log.w(TAG, "@After - Depois do teste - Inicio")
        limpaBaseDeTestes()
        Log.w(TAG, "@After - Depois do teste - Fim")
    }

    @Test
    fun deve_RetornarTrueESalvarUmUsuario_QuandoRecebeUmUsuario() {

        Log.w(TAG, "Inicio teste")

        val usuario = UsuarioTest.geraModelCompleto(RandomUtil.geraNumeroIntervalo(1, 50))
        usuarioDao.inserir(usuario)
        Log.w(TAG, "Inseriu o usuario " + usuario.id + " - " + usuario.nome)

        val usuarioTeste = usuarioDao.buscaPorId(usuario.id)
        Log.w(TAG, "Buscou o usuario " + usuario.id + " - " + usuario.nome)

        Assert.assertEquals(usuarioTeste.id, usuario.id)

        Log.w(TAG, "Final teste")

    }

    private fun limpaBaseDeTestes() {

        database?.clearAllTables()
        Log.w(TAG, "Limpou a base de dados")

        Assert.assertEquals(usuarioDao.quantidadeUsuarios(), 0)
    }

}