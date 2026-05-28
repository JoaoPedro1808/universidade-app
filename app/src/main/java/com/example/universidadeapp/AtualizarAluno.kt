package com.example.universidadeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AtualizarAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atualizar_aluno)

        val etMatriculaAtualizar = findViewById<EditText>(R.id.etMatriculaAtualizar)
        val etNomeAtualizar = findViewById<EditText>(R.id.etNomeAtualizar)
        val etIdadeAtualizar = findViewById<EditText>(R.id.etIdadeAtualizar)
        val etSexoAtualizar = findViewById<EditText>(R.id.etSexoAtualizar)
        val btAtualizarAluno = findViewById<Button>(R.id.btAtualizarAluno)

        btAtualizarAluno.setOnClickListener {
            val matriculaStr = etMatriculaAtualizar.text.toString().trim()
            val nome = etNomeAtualizar.text.toString().trim()
            val idadeStr = etIdadeAtualizar.text.toString().trim()
            val sexo = etSexoAtualizar.text.toString().trim()

            if (matriculaStr.isEmpty()) {
                Toast.makeText(this@AtualizarAluno, "Por favor informe a matrícula do aluno", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val matricula = matriculaStr.toInt()
            val idade = idadeStr.toInt()

            AtualizarDadosAluno(nome, matricula, sexo, idade)

            val voltar = Intent(this, MainActivity::class.java)
            startActivity(voltar)
        }
    }

    private fun AtualizarDadosAluno(nome: String, matricula: Int, sexo: String, idade: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val alunoAtualizado = RetrofitClient.api.atualizarAluno(nome, matricula, sexo, idade)

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AtualizarAluno, "Aluno ${alunoAtualizado.nome} teve os dados modificados", Toast.LENGTH_LONG).show()
                    finish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AtualizarAluno, "Erro ao atualizar os dados do usuario: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}