package com.example.findgit.model

import android.util.Log
import com.example.findgit.util.RandomUtil
import java.util.*

class ProjetoTest(
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
) : Projeto(
    id,
    dhCriacao,
    dhUltAtualizacao,
    idDono,
    nome,
    descricao,
    urlGit,
    urlPagina,
    linguagem,
    branchPrincipal,
    totalForks
) {

    companion object {

        private val TAG = ProjetoTest::class.java.simpleName

        fun geraModelCompleto(idDono: Long?): ProjetoTest {

            val projetoTest = ProjetoTest(
                1L,
                Calendar.getInstance(),
                Calendar.getInstance(),
                if (idDono != null) idDono else 1L,
                "${RandomUtil.geraNomeRandom()}",
                "${RandomUtil.geraTextoRandom(5)}",
                "${RandomUtil.geraTextoRandom(5)}",
                "${RandomUtil.geraTextoRandom(5)}",
                "${RandomUtil.geraLugarRandom()}",
                "Master",
                RandomUtil.geraNumeroRandom(6)
            )

            Log.w(TAG, "Dados do projeto: $projetoTest")

            return projetoTest
        }
    }
}