package com.example.findgit.ui.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findgit.R
import com.example.findgit.form.ProjetoForm
import com.example.findgit.ui.adapter.ProjetoAdapter
import com.example.findgit.ui.viewModel.ProjetoViewModel
import com.example.findgit.util.CarregandoUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var projetoAdapter: ProjetoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ProjetoViewModel
    private lateinit var lista: MutableLiveData<List<ProjetoForm>>
    private lateinit var dialogCarregando: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.list_repo_salvas)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        dialogCarregando = CarregandoUtil(this, this).show()

        viewModel = ProjetoViewModel(this)
        viewModel.buscarListagemProjetos()
        lista = viewModel.listagemProjetos

        projetoAdapter = ProjetoAdapter(lista.value!!.toMutableList(), {})
        recyclerView.adapter = projetoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        listeners()
        dialogCarregando.dismiss()
    }

    private fun listeners() {
        fb_buscar.setOnClickListener {
            val myIntent = Intent(this, ListaProjetosActivity::class.java)
            startActivity(myIntent)
        }

        observerListagem()
    }

    private fun observerListagem() {
        viewModel.listagemProjetos.observe(this, Observer { projetos ->

            if (projetos.isEmpty()) {
                cl_nenhum_projeto_encontrado.visibility = View.VISIBLE
                list_repo_salvas.visibility = View.GONE
            } else {
                cl_nenhum_projeto_encontrado.visibility = View.GONE
                list_repo_salvas.visibility = View.VISIBLE

                recyclerView.adapter =
                    ProjetoAdapter(lista.value!!.toMutableList(), { vizualizaItem(it) })

                recyclerView = findViewById<RecyclerView>(R.id.list_repo_salvas).apply {
                    setHasFixedSize(true)
                    dialogCarregando.dismiss()
                }
            }
        })
    }

    private fun vizualizaItem(projeto: ProjetoForm) {
        val intent = Intent(this, ProjetoActivity::class.java)
        intent.putExtra("projeto", projeto)
        intent.putExtra("itemBusca", false)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.buscarListagemProjetos()
        lista = viewModel.listagemProjetos
        observerListagem()
    }
}
