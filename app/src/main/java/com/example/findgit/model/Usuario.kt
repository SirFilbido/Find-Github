package com.example.findgit.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.findgit.form.UsuarioForm
import java.io.Serializable

@Entity
open class Usuario : Serializable {

    @PrimaryKey()
    @ColumnInfo(name = "ID")
    var id: Long = 0

    @ColumnInfo(name = "NOME")
    var nome: String = ""

    @ColumnInfo(name = "URL_AVATAR")
    var urlAvatar: String = ""

    @ColumnInfo(name = "URL_GIT")
    var urlGit: String = ""

    @ColumnInfo(name = "TIPO")
    var tipo: String = ""

    constructor()

    constructor(form: UsuarioForm) {
        this.id = form.id
        this.nome = form.nome
        this.urlAvatar = form.urlAvatar
        this.urlGit = form.urlGit
        this.tipo = form.tipo
    }

    @Ignore
    constructor(id: Long, nome: String, urlAvatar: String, urlGit: String, tipo: String) {
        this.id = id
        this.nome = nome
        this.urlAvatar = urlAvatar
        this.urlGit = urlGit
        this.tipo = tipo
    }


    override fun toString(): String {
        return "Usuario(id=$id, nome='$nome', urlAvatar='$urlAvatar', " +
                "urlGit='$urlGit', tipo='$tipo')"
    }

}