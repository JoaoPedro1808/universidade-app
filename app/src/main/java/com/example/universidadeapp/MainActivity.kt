package com.example.universidadeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btNovoAluno = findViewById<Button>(R.id.btNovoAluno)
        val btListarAlunos = findViewById<Button>(R.id.btListarAlunos)
        val btBuscarAluno = findViewById<Button>(R.id.btBuscarALuno)
        val btRemoverAluno = findViewById<Button>(R.id.btRemoverAluno)
        val btAtualizarAluno = findViewById<Button>(R.id.btAtualizarAluno)

        btNovoAluno.setOnClickListener {
            val intent = Intent(this, InserirNovoAluno::class.java)
            startActivity(intent)
        }

        btListarAlunos.setOnClickListener {
            Toast.makeText(this, "Conectado com a API (Listar alunos)", Toast.LENGTH_SHORT).show()
            testeListarAlunos()
        }

        btBuscarAluno.setOnClickListener {
            Toast.makeText(this, "Conectado com a API (Buscar aluno por matricula)", Toast.LENGTH_SHORT).show()
        }

        btRemoverAluno.setOnClickListener {
            Toast.makeText(this, "Conectado com a API (Remover alunos especifico)", Toast.LENGTH_SHORT).show()
        }

        btAtualizarAluno.setOnClickListener {
            Toast.makeText(this, "Conectado com a API (Atualizar dados do aluno)", Toast.LENGTH_SHORT).show()
        }

    }

    private fun testeListarAlunos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val listaAlunos = RetrofitClient.api.listarAlunos()

                withContext(Dispatchers.Main) {
                    if (listaAlunos.isEmpty()) {
                        Toast.makeText(this@MainActivity, "Nenhum cadastro", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "${listaAlunos.size} alunos encontrados!", Toast.LENGTH_SHORT).show()

                        for (aluno in listaAlunos) {
                            Log.d("API_TESTE", "Aluno: ${aluno.nome} | Matricula: ${aluno.matricula}")
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Erro na api: ${e.message}", Toast.LENGTH_SHORT).show()
                    Log.e("API_ERRO", "Erro detalhado", e)
                }
            }
        }
    }
}