package com.example.findgit.holder

class ProjetoHolder(
    var id: Long = 0,
    var created_at: String = "",
    var updated_at: String = "",
    var owner: UsuarioHolder = UsuarioHolder(),
    var name: String? = null,
    var description: String? = null,
    var html_url: String? = null,
    var homepage: String? = null,
    var language: String? = null,
    var default_branch: String? = null,
    var forks_count: Long? = null
) {

    override fun toString(): String {
        return "ProjetoHolder(id=$id, created_at=$created_at, updated_at=$updated_at, " +
                "owner=$owner, name=$name, description=$description, html_url=$html_url, " +
                "homepage=$homepage, language=$language, default_branch=$default_branch, " +
                "forks_count=$forks_count)"
    }
}