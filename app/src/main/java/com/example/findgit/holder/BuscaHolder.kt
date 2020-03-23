package com.example.findgit.holder

class BuscaHolder(
    var total_count: Long = 0,
    var incomplete_results: Boolean = false,
    var items: List<ProjetoHolder>
) {

    override fun toString(): String {
        return "BuscaHolder(total_count=$total_count, " +
                "incomplete_results=$incomplete_results, items=$items)"
    }
}