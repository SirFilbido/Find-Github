package com.example.findgit.model

import android.util.Log
import com.example.findgit.util.RandomUtil

class UsuarioTest(
    id: Long,
    nome: String,
    urlAvatar: String,
    urlGit: String,
    tipo: String
) : Usuario(id, nome, urlAvatar, urlGit, tipo) {

    companion object {

        private val TAG = UsuarioTest::class.java.simpleName

        fun geraModelCompleto(id: Long?): UsuarioTest {

            val usuario = UsuarioTest(
                if (id != null) id else 1L,
                "${RandomUtil.geraNomeRandom()}",
                "",
                "",
                ""
            )

            Log.w(TAG, "Dados do usuario: $usuario")

            return usuario
        }
    }
}