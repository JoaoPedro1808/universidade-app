package com.example.universidadeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BuscarAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_aluno)

        val etMatricula = findViewById<EditText>(R.id.etMatricula)
        val btBuscarAluno = findViewById<Button>(R.id.btBuscarAluno)
        val layoutResultado = findViewById<LinearLayout>(R.id.layoutResultado)
        val tvResultadoNome = findViewById<TextView>(R.id.tvResultadoNome)
        val tvResultadoDetalhe = findViewById<TextView>(R.id.tvResultadoDetalhe)
        val btVoltar = findViewById<Button>(R.id.btVoltar)

        btBuscarAluno.setOnClickListener {
            val matricula = etMatricula.text.toString().toIntOrNull() ?: return@setOnClickListener

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val aluno = RetrofitClient.api.buscarAluno(matricula)

                    withContext(Dispatchers.Main) {
                        tvResultadoNome.text = "Nome: ${aluno.nome}"
                        tvResultadoDetalhe.text = "Idade: ${aluno.idade} | Sexo: ${aluno.sexo}"

                        layoutResultado.visibility = View.VISIBLE
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@BuscarAluno, "Erro ao salvar aluno: ${e.message}", Toast.LENGTH_LONG).show()
                        layoutResultado.visibility = View.GONE
                    }
                }
            }
        }

        btVoltar.setOnClickListener {
            finish()
            val voltar = Intent(this, MainActivity::class.java)
            startActivity(voltar)
        }

    }
}