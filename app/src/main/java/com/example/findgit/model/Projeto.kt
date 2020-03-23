package com.example.findgit.model

import androidx.room.*
import com.example.findgit.form.ProjetoForm
import java.util.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["ID"],
            childColumns = ["ID_DONO"]
        )
    ]
)
open class Projeto {

    @PrimaryKey()
    @ColumnInfo(name = "ID")
    var id: Long = 0

    @ColumnInfo(name = "DH_CRIACAO")
    var dhCriacao: Calendar = Calendar.getInstance()

    @ColumnInfo(name = "DH_ALT_ATUALIZACAO")
    var dhUltAtualizacao: Calendar = Calendar.getInstance()

    @ColumnInfo(name = "ID_DONO")
    var idDono: Long = 0

    @ColumnInfo(name = "NOME")
    var nome: String = ""

    @ColumnInfo(name = "DESCRICAO")
    var descricao: String? = null

    @ColumnInfo(name = "URL_GIT")
    var urlGit: String = ""

    @ColumnInfo(name = "URL_PAGINA")
    var urlPagina: String? = null

    @ColumnInfo(name = "LINGUAGEM")
    var linguagem: String? = null

    @ColumnInfo(name = "BRANCH_PRINCIPAL")
    var branchPrincipal: String = ""

    @ColumnInfo(name = "TOTAL_FORKS")
    var totalForks: Long = 0

    constructor()

    constructor(form: ProjetoForm) {
        this.id = form.id
        this.dhCriacao = form.dhCriacao
        this.dhUltAtualizacao = form.dhUltAtualizacao
        this.idDono = form.dono.id
        this.nome = form.nome!!
        this.descricao = form.descricao!!
        this.urlGit = form.urlGit!!
        this.urlPagina = form.urlPagina
        this.linguagem = form.linguagem
        this.branchPrincipal = form.branchPrincipal!!
        this.totalForks = form.totalForks!!
    }

    @Ignore
    constructor(
        id: Long,
        dhCriacao: Calendar,
        dhUltAtualizacao: Calendar,
        idDono: Long,
        nome: String,
        descricao: String,
        urlGit: String,
        urlPagina: String?,
        linguagem: String?,
        branchPrincipal: String,
        totalForks: Long
    ) {
        this.id = id
        this.dhCriacao = dhCriacao
        this.dhUltAtualizacao = dhUltAtualizacao
        this.idDono = idDono
        this.nome = nome
        this.descricao = descricao
        this.urlGit = urlGit
        this.urlPagina = urlPagina
        this.linguagem = linguagem
        this.branchPrincipal = branchPrincipal
        this.totalForks = totalForks
    }


    override fun toString(): String {
        return "Projeto(id=$id, dhCriacao=$dhCriacao, dhUltAtualizacao=$dhUltAtualizacao, " +
                "idDono=$idDono, nome='$nome', descricao='$descricao', urlGit='$urlGit', " +
                "urlPagina=$urlPagina, linguagem=$linguagem, branchPrincipal='$branchPrincipal', " +
                "totalForks=$totalForks)"
    }

}