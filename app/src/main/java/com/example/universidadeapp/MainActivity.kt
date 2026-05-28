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
            val intent = Intent(this, ListarAlunos::class.java)
            startActivity(intent)
        }

        btBuscarAluno.setOnClickListener {
            val intent = Intent(this, BuscarAluno::class.java)
            startActivity(intent)
        }

        btRemoverAluno.setOnClickListener {
            val intent = Intent(this, RemoverAluno::class.java)
            startActivity(intent)
        }

        btAtualizarAluno.setOnClickListener {
            val intent = Intent(this, AtualizarAluno::class.java)
            startActivity(intent)
        }
    }
}