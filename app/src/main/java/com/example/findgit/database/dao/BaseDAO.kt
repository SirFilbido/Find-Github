package com.example.findgit.database.dao

import androidx.annotation.WorkerThread
import androidx.room.*

@Dao
interface BaseDAO<T> {

    /**
     * Insere um objeto no banco de dados
     *
     * @param obj
     */
    @Insert
    fun inserir(obj: T)

    /**
     * Insere um array de objetos no banco de dados
     *
     * @param list
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @WorkerThread
    fun inserir(list: List<T>)

    /**
     * Atualizar um objeto no banco de dados
     *
     * @param obj
     */
    @Update
    fun atualizar(obj: T)

    /**
     * Remove um objeto do banco de dados
     *
     * @param obj
     */
    @Delete
    fun remover(obj: T)
}