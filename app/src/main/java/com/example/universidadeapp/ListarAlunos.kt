package com.example.universidadeapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListarAlunos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_alunos)

        buscarEExibirAluno()
    }

    private fun buscarEExibirAluno() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val listaDaApi = RetrofitClient.api.listarAlunos()

                withContext(Dispatchers.Main) {
                    if (listaDaApi.isEmpty()) {
                        Toast.makeText(this@ListarAlunos, "Nenhum aluno encontrado", Toast.LENGTH_SHORT).show()
                    } else {
                        val recyclerView = findViewById<RecyclerView>(R.id.rvAlunos)
                        recyclerView.layoutManager = LinearLayoutManager(this@ListarAlunos)
                        recyclerView.adapter = AlunoAdapter(listaDaApi)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ListarAlunos, "Erro ${e}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}