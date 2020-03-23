package com.example.findgit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.findgit.BuildConfig
import com.example.findgit.database.dao.ProjetoDAO
import com.example.findgit.database.dao.UsuarioDAO
import com.example.findgit.model.Projeto
import com.example.findgit.model.Usuario
import com.example.findgit.util.CalendarConverter

@Database(
    entities = [
        Usuario::class,
        Projeto::class
    ], version = BuildConfig.VERSAO_BD, exportSchema = false
)
@TypeConverters(CalendarConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDAO(): UsuarioDAO
    abstract fun projetoDAO(): ProjetoDAO
}

object Database {

    private lateinit var database: AppDatabase

    fun instance(context: Context): AppDatabase {
        if (::database.isInitialized) return database
        database = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                BuildConfig.NOME_BD
            )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
        return database
    }

}