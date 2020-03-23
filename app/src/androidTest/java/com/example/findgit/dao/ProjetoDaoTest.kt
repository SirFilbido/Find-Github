package com.example.findgit.dao

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.findgit.database.AppDatabase
import com.example.findgit.database.Database
import com.example.findgit.database.dao.ProjetoDAO
import com.example.findgit.database.dao.UsuarioDAO
import com.example.findgit.model.ProjetoTest
import com.example.findgit.model.UsuarioTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProjetoDaoTest {

    private val TAG = javaClass.simpleName
    private var database: AppDatabase? = null
    val context = ApplicationProvider.getApplicationContext<Context>()

    private lateinit var projetoDao: ProjetoDAO
    private lateinit var usuarioDao: UsuarioDAO

    @Before
    fun antesDeExecutar() {
        Log.w(TAG, "@Before - Antes do teste - Inicio")
        database = Database.instance(context)
        projetoDao = database?.projetoDAO()!!
        usuarioDao = database?.usuarioDAO()!!

        limpaBaseDeTestes()

        usuarioDao.inserir(UsuarioTest.geraModelCompleto(null))
        Log.w(TAG, "@Before - Antes do teste - Fim")
    }

    @After
    fun aposExecutar() {
        Log.w(TAG, "@After - Depois do teste - Inicio")
        limpaBaseDeTestes()
        Log.w(TAG, "@After - Depois do teste - Fim")
    }

    @Test
    fun deve_RetornarTrueESalvarUmProjeto_QuandoRecebeUmProjeto() {

        Log.w(TAG, "Inicio teste")

        val projeto = ProjetoTest.geraModelCompleto(null)
        projetoDao.inserir(projeto)
        Log.w(TAG, "Inseriu o projeto " + projeto.id + " - " + projeto.nome)

        val projetoTest = projetoDao.buscaPorId(projeto.id)
        Log.w(TAG, "Buscou o projeto " + projeto.id + " - " + projeto.nome)

        Assert.assertEquals(projetoTest.id, projeto.id)

        Log.w(TAG, "Final teste")

    }

    private fun limpaBaseDeTestes() {

        database?.clearAllTables()
        Log.w(TAG, "Limpou a base de dados")

        Assert.assertEquals(usuarioDao.quantidadeUsuarios(), 0)
        Assert.assertEquals(projetoDao.quantidadeProjetos(), 0)
    }

}