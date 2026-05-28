package com.example.universidadeapp

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UniversitarioAPI {
    @GET("/api/universitarios/lista-alunos")
    suspend fun listarAlunos() : List<Universitario>

    @GET("/api/universitarios/buscar-aluno")
    suspend fun buscarAluno(
        @Query("matricula") matricula: Int
    ) : Universitario

    @POST("/api/universitarios/novo-aluno")
    suspend fun inserirNovoAluno(
        @Query("nome") nome: String,
        @Query("matricula") matricula: Int,
        @Query("sexo") sexo: String,
        @Query("idade") idade: Int,
        @Query("nota") nota: Float
    ) : Universitario

    @DELETE("/api/universitarios/remover-aluno")
    suspend fun deletarAluno(
        @Query("matricula") matricula: Int
    ) : Universitario

    @PUT("/api/universitarios/atualizar-aluno")
    suspend fun atualizarAluno(
        @Query("matricula") matricula: Int,
        @Query("nota") nota: Float
    ) : Response<Unit>
}