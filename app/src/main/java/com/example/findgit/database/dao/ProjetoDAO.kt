package com.example.findgit.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.findgit.model.Projeto

@Dao
interface ProjetoDAO : BaseDAO<Projeto> {

    @Query("SELECT * FROM Projeto")
    fun buscaTodos(): List<Projeto>

    @Query("SELECT * FROM Projeto WHERE id = :id")
    fun buscaPorId(id: Long): Projeto

    @Query("SELECT count(*) FROM Projeto")
    fun quantidadeProjetos(): Int
}