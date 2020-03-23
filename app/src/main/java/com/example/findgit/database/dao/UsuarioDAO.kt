package com.example.findgit.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.findgit.model.Usuario

@Dao
interface UsuarioDAO : BaseDAO<Usuario> {

    @Query("SELECT * FROM Usuario WHERE id = :id")
    fun buscaPorId(id: Long): Usuario

    @Query("SELECT count(*) FROM Usuario")
    fun quantidadeUsuarios(): Int
}