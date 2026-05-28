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
import retrofit2.Response


class AtualizarAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atualizar_aluno)

        val etMatriculaAtualizar = findViewById<EditText>(R.id.etMatriculaAtualizar)
        val etNota = findViewById<EditText>(R.id.etNota)
        val btAtualizarAluno = findViewById<Button>(R.id.btAtualizarAluno)

        btAtualizarAluno.setOnClickListener {
            val matriculaStr = etMatriculaAtualizar.text.toString().trim()
            val notaStr = etNota.text.toString().trim()

            if (matriculaStr.isEmpty()) {
                Toast.makeText(this@AtualizarAluno, "Por favor informe a matrícula do aluno", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val matricula = matriculaStr.toInt()
            val nota = notaStr.toFloat()

            AtualizarDadosAluno(matricula, nota)

            val voltar = Intent(this, MainActivity::class.java)
            startActivity(voltar)
        }
    }

    private fun AtualizarDadosAluno(matricula: Int, nota: Float) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val alunoAtualizado = RetrofitClient.api.atualizarAluno(matricula, nota)

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AtualizarAluno, "Dados do aluno atualizados", Toast.LENGTH_LONG).show()
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