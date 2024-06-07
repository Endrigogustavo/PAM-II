package com.example.crud_kotlin.viewModel

import com.example.crud_kotlin.RoomBD.Pessoa
import com.example.crud_kotlin.RoomBD.PessoaDataBase

class Repository(private val db: PessoaDataBase) {
    //Utilizando os comandos de CRUD com base no que vai ser enviado do form
    suspend fun upsertPessoa(pessoa: Pessoa){
        db.pessoaDao().updatePessoa(pessoa)
    }

    suspend fun deletePessoa(pessoa: Pessoa){
        db.pessoaDao().deletePessoa(pessoa)
    }

    fun getAllPessoa() = db.pessoaDao().getAllPessoa()

}