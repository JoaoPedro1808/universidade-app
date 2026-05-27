package com.example.universidadeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RemoverAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remover_aluno)

        val etMatricula = findViewById<EditText>(R.id.etMatricula)
        val btRemoverAluno = findViewById<Button>(R.id.btRemoverAluno)

        btRemoverAluno.setOnClickListener {
            val matriculaStr = etMatricula.text.toString().trim()

            if (matriculaStr.isEmpty()) {
                Toast.makeText(this@RemoverAluno, "Por favor cadastre a matricula do aluno", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val matricula = matriculaStr.toInt()

            DeletarAlunoNoBanco(matricula)

            val voltar = Intent(this, MainActivity::class.java)
            startActivity(voltar)
        }

    }

    private fun DeletarAlunoNoBanco(matricula: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val alunoRemovido = RetrofitClient.api.deletarAluno(matricula)

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RemoverAluno, "Aluno: ${alunoRemovido.nome} removido com sucesso", Toast.LENGTH_LONG).show()
                    finish()
                }
            } catch (e: Exception) {
                Toast.makeText(this@RemoverAluno, "Erro ao remover aluno: ${e}", Toast.LENGTH_LONG).show()
            }
        }
    }
}