package com.example.universidadeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AlunoAdapter(private val listaAlunos: List<Universitario>) : RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_aluno, parent, false)
        return AlunoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlunoViewHolder, position: Int) {
        val aluno = listaAlunos[position]
        holder.tvNome.text = aluno.nome
        holder.tvMatricula.text = "Matrícula: ${aluno.matricula} | Sexo: ${aluno.sexo} | Idade: ${aluno.idade} | Nota: ${aluno.nota}"
    }

    override fun getItemCount(): Int = listaAlunos.size

    class AlunoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView = itemView.findViewById(R.id.tvNomeItem)
        val tvMatricula: TextView = itemView.findViewById(R.id.tvMatriculaItem)
    }
}