package com.example.findgit.form

import com.example.findgit.holder.ProjetoHolder
import com.example.findgit.model.Projeto
import com.example.findgit.repository.UsuarioRepository
import com.example.findgit.util.DateFormatUtil
import java.io.Serializable
import java.util.*

class ProjetoForm : Serializable {

    var id: Long = 0
    var dhCriacao: Calendar = Calendar.getInstance()
    var dhUltAtualizacao: Calendar = Calendar.getInstance()
    var dono: UsuarioForm = UsuarioForm()
    var nome: String? = null
    var descricao: String? = null
    var urlGit: String? = null
    var urlPagina: String? = null
    var linguagem: String? = null
    var branchPrincipal: String? = null
    var totalForks: Long? = null

    constructor()

    constructor(holder: ProjetoHolder) {
        this.id = holder.id
        this.dhCriacao =
            DateFormatUtil.stringToCalendar(holder.created_at, DateFormatUtil.FORMATO_RETROFIT)
        this.dhUltAtualizacao =
            DateFormatUtil.stringToCalendar(
                holder.updated_at,
                DateFormatUtil.FORMATO_RETROFIT
            )
        this.dono = UsuarioForm(holder.owner)
        this.nome = holder.name!!
        this.descricao = holder.description
        this.urlGit = holder.html_url!!
        this.urlPagina = holder.homepage
        this.linguagem = holder.language
        this.branchPrincipal = holder.default_branch!!
        this.totalForks = holder.forks_count!!
    }

    constructor(model: Projeto, repositoryUsuario: UsuarioRepository) {
        this.id = model.id
        this.dhCriacao = model.dhCriacao
        this.dhUltAtualizacao = model.dhUltAtualizacao
        this.dono = UsuarioForm(repositoryUsuario.buscaPorId(model.idDono)!!)
        this.nome = model.nome
        this.descricao = model.descricao
        this.urlGit = model.urlGit
        this.urlPagina = model.urlPagina
        this.linguagem = model.linguagem
        this.branchPrincipal = model.branchPrincipal
        this.totalForks = model.totalForks
    }

    override fun toString(): String {
        return "ProjetoForm(id=$id, dhCriacao=$dhCriacao, dhUltAtualizacao=$dhUltAtualizacao, " +
                "dono=$dono, nome=$nome, descricao=$descricao, urlGit=$urlGit, urlPagina=$urlPagina, " +
                "linguagem=$linguagem, branchPrincipal=$branchPrincipal, totalForks=$totalForks)"
    }
}