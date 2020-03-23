package com.example.findgit.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findgit.R
import com.example.findgit.form.ProjetoForm
import com.example.findgit.util.DateFormatUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_projeto.view.*

class ProjetoAdapter(
    private val projetos: MutableList<ProjetoForm>,
    val clickListener: ((ProjetoForm) -> Unit)
) : RecyclerView.Adapter<ProjetoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_projeto, parent, false)

        return ViewHolder(view)
    }

    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        var ivAvatar: ImageView? = itemView.findViewById(R.id.iv_avatar)
        var tvNomeProjeto: TextView = itemView.tv_nome_projeto
        var tvDescricao: TextView = itemView.tv_descricao
        var tvDhCriacao: TextView = itemView.tv_dh_cricacao
        var tvDono: TextView = itemView.tv_dono

        fun bindView(projeto: ProjetoForm) {
            Picasso.get().load(projeto.dono.urlAvatar).into(ivAvatar)
            tvNomeProjeto.text = projeto.nome
            tvDescricao.text = projeto.descricao
            tvDhCriacao.text =
                DateFormatUtil.formatar(projeto.dhCriacao, DateFormatUtil.FORMATO_DATA)
            tvDono.text = projeto.dono.nome
        }

        init {
            itemView.setOnClickListener {
                clickListener(projetos[adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(projetos[position])
    }

    override fun getItemCount() = projetos.size
}