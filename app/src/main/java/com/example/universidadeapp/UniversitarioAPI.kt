package com.example.universidadeapp

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UniversitarioAPI {
    @GET("api/universitarios/lista-aluno")
    suspend fun listarAlunos() : List<Universitario>

    @GET("api/universitario/buscar-aluno")
    suspend fun buscarAluno(
        @Query("matricula") matricula: Int
    ) : Universitario

    @POST("api/universitario/novo-aluno")
    suspend fun inserirNovoAluno(
        @Query("nome") nome: String,
        @Query("matricula") matricula: String,
        @Query("sexo") sexo: String,
        @Query("idade") idade: String
    ) : Universitario

    @DELETE("api/universitario/remover-aluno")
    suspend fun deletarAluno(
        @Query("matricula") matricula: Int
    ) : Universitario

    @PUT("api/universitario/atualizar-aluno")
    suspend fun atualizarAluno(
        @Query("nome") nome: String,
        @Query("matricula") matricula: Int,
        @Query("sexo") sexo: String,
        @Query("idade") idade: String
    ) : Universitario
}