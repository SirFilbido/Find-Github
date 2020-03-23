package com.example.findgit.form

import com.example.findgit.holder.UsuarioHolder
import com.example.findgit.model.Usuario
import java.io.Serializable

class UsuarioForm : Serializable {

    var id: Long = 0
    var nome: String = ""
    var urlAvatar: String = ""
    var urlGit: String = ""
    var tipo: String = ""

    constructor()

    constructor(holder: UsuarioHolder) {
        this.id = holder.id
        this.nome = holder.login
        this.urlAvatar = holder.avatar_url
        this.urlGit = holder.html_url
        this.tipo = holder.type
    }

    constructor(model: Usuario) {
        this.id = model.id
        this.nome = model.nome
        this.urlAvatar = model.urlAvatar
        this.urlGit = model.urlGit
        this.tipo = model.tipo
    }

    override fun toString(): String {
        return "UsuarioForm(id=$id, nome=$nome, urlAvatar=$urlAvatar, urlGit=$urlGit, tipo=$tipo)"
    }
}