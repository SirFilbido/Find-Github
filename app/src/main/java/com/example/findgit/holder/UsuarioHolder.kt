package com.example.findgit.holder

class UsuarioHolder(
    var id: Long = 0,
    var login: String = "",
    var avatar_url: String = "",
    var html_url: String = "",
    var type: String = ""
) {

    override fun toString(): String {
        return "UsuarioHolder(id=$id, login=$login, avatar_url=$avatar_url, " +
                "html_url=$html_url, type=$type)"
    }
}