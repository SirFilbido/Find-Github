package com.example.findgit.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.findgit.R
import com.example.findgit.form.ProjetoForm
import com.example.findgit.ui.viewModel.ProjetoViewModel
import com.example.findgit.util.DateFormatUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_projeto.*

class ProjetoActivity : AppCompatActivity() {

    private lateinit var projeto: ProjetoForm
    private var itemBusca: Boolean = false
    private lateinit var viewModel: ProjetoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projeto)

        projeto = intent.getSerializableExtra("projeto") as ProjetoForm
        itemBusca = intent.getSerializableExtra("itemBusca") as Boolean

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "${getText(R.string.pagina)} ${projeto.nome}"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel = ProjetoViewModel(this)

        if (!itemBusca)
            bt_salvar.visibility = View.GONE

        preencheValores()
        listeners()
    }

    private fun preencheValores() {

        val avatar: ImageView = findViewById(R.id.iv_avatar)
        Picasso.get().load(projeto.dono.urlAvatar).into(avatar)

        tv_dono.text = projeto.dono.nome
        tv_nome_projeto.text = projeto.nome
        tv_descricao.text = projeto.descricao
        tv_dh_cricacao.text =
            DateFormatUtil.formatar(projeto.dhCriacao, DateFormatUtil.FORMATO_DATA)
        tv_dh_ult_atualizacao.text =
            DateFormatUtil.formatar(projeto.dhUltAtualizacao, DateFormatUtil.FORMATO_DATA)
        tv_linguagem.text = projeto.linguagem
        tv_branch.text = projeto.branchPrincipal
        tv_fork.text = projeto.totalForks.toString()
        tv_git.text = projeto.urlGit
        if (projeto.urlPagina != null) tv_mais.text = projeto.urlPagina
    }

    private fun listeners() {
        bt_salvar.setOnClickListener {
            viewModel.salvarProjeto(projeto) {
                finish()
                Toast.makeText(this, getText(R.string.projetoSalvo), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}