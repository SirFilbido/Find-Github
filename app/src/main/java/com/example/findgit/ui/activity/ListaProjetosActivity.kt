package com.example.findgit.ui.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findgit.R
import com.example.findgit.form.ProjetoForm
import com.example.findgit.service.ProjetoService
import com.example.findgit.ui.adapter.ProjetoAdapter
import com.example.findgit.ui.viewModel.ProjetoViewModel
import com.example.findgit.util.CarregandoUtil
import kotlinx.android.synthetic.main.activity_listagem_projetos.*

class ListaProjetosActivity : AppCompatActivity() {

    private val service = ProjetoService
    private var lista: MutableList<ProjetoForm> = mutableListOf()
    private var pagina = 1
    private lateinit var projetoAdapter: ProjetoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var dialogCarregando: AlertDialog
    private lateinit var etTermo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem_projetos)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        recyclerView = findViewById(R.id.list_repo_busca)
        etTermo = findViewById(R.id.et_termo) as EditText

        projetoAdapter = ProjetoAdapter(lista, {})
        recyclerView.adapter = projetoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        listeners()
        validaListagem()
    }

    private fun vizualizaItem(projeto: ProjetoForm) {
        val intent = Intent(this, ProjetoActivity::class.java)
        intent.putExtra("projeto", projeto)
        intent.putExtra("itemBusca", true)
        startActivity(intent)
    }

    private fun listeners() {

        bt_buscar.setOnClickListener {
            pagina = 1
            buscaTermoDigitado(pagina)
        }

        bt_proximo.setOnClickListener {
            buscaTermoDigitado(++pagina)
        }

        bt_anterior.setOnClickListener {
            buscaTermoDigitado(--pagina)
        }
    }

    private fun buscaTermoDigitado(pagina: Int) {

        fechaKeyboard(this)
        dialogCarregando = CarregandoUtil(this, this).show()

        if (etTermo.text.isEmpty()) {
            Toast.makeText(this, getText(R.string.campoBuscaV), Toast.LENGTH_SHORT).show()
            dialogCarregando.dismiss()
        } else {
            service.buscaProjetosPorTermo(etTermo.text.toString(), pagina, {
                recyclerView.adapter = ProjetoAdapter(it, { vizualizaItem(it) })

                if (it.isEmpty())
                    Toast.makeText(this, getText(R.string.erroLista), Toast.LENGTH_SHORT).show()

                recyclerView = findViewById<RecyclerView>(R.id.list_repo_busca).apply {
                    setHasFixedSize(true)
                    dialogCarregando.dismiss()
                }
                lista = it
                validaListagem()
            }, {
                dialogCarregando.dismiss()
            })
        }
    }

    private fun validaListagem() {
        if (lista.isEmpty()) {
            cl_nenhum_projeto.visibility = View.VISIBLE
            list_repo_busca.visibility = View.GONE
            bt_proximo.visibility = View.INVISIBLE
            bt_anterior.visibility = View.INVISIBLE
            tv_pagina.visibility = View.INVISIBLE
        } else {
            cl_nenhum_projeto.visibility = View.GONE
            list_repo_busca.visibility = View.VISIBLE
            bt_proximo.visibility = View.VISIBLE
            tv_pagina.visibility = View.VISIBLE
            tv_pagina.text = "${getText(R.string.pagina)} $pagina"
            if (pagina <= 1) bt_anterior.visibility = View.INVISIBLE
            else bt_anterior.visibility = View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun fechaKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (null != activity.currentFocus) imm.hideSoftInputFromWindow(
            activity.currentFocus!!.applicationWindowToken, 0
        )
    }
}