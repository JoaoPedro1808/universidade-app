package com.example.universidadeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InserirNovoAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inserir_novo_aluno)

        val etMatricula = findViewById<EditText>(R.id.etMatricula)
        val etNome = findViewById<EditText>(R.id.etNome)
        val etIdade = findViewById<EditText>(R.id.etIdade)
        val etSexo = findViewById<EditText>(R.id.etSexo)
        val btSalvarAluno = findViewById<Button>(R.id.btSalvarAluno)

        btSalvarAluno.setOnClickListener {
            val matriculaStr = etMatricula.text.toString().trim()
            val nome = etNome.text.toString().trim()
            val idadeStr = etIdade.text.toString().trim()
            val sexo = etSexo.text.toString().trim()

            if (matriculaStr.isEmpty() || nome.isEmpty() || idadeStr.isEmpty() || sexo.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val matricula = matriculaStr.toInt()
            val idade = idadeStr.toInt()

            salvarAlunoNoServidor(nome, matricula, sexo, idade)

            val voltar = Intent(this, MainActivity::class.java)
            startActivity(voltar)
        }
    }

    private fun salvarAlunoNoServidor(nome: String, matricula: Int, sexo: String, idade: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val alunoCriado = RetrofitClient.api.inserirNovoAluno(nome, matricula, sexo, idade)

                // Volta para a Thread principal para exibir o resultado visual
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@InserirNovoAluno, "Aluno ${alunoCriado.nome} inserido com sucesso!", Toast.LENGTH_LONG).show()

                    finish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@InserirNovoAluno, "Erro ao salvar aluno: ${e.message}", Toast.LENGTH_LONG).show()
                    Log.e("API_CADASTRO_ERRO", "Erro na rota novo-aluno", e)
                }
            }
        }
    }
}