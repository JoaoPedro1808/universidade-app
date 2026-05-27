package com.example.universidadeapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AtualizarAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atualizar_aluno)

        val etMatriculaAtualizar = findViewById<EditText>(R.id.etMatriculaAtualizar)
        val etNomeAtualizar = findViewById<EditText>(R.id.etNomeAtualizar)
        val etIdadeAtualizar = findViewById<EditText>(R.id.etIdadeAtualizar)
        val etSexoAtualizar = findViewById<EditText>(R.id.etSexoAtualizar)
        val btAtualizarAluno = findViewById<Button>(R.id.btAtualizarAluno)
    }

//    private fun AtualizarDadosAluno(nome: String, matricula: Int, sexo: String, idade: Int) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val alunoAtualizado = RetrofitClient.api.atualizarAluno(nome, matricula, sexo, idade)
//
//
//            }
//        }
//    }
}