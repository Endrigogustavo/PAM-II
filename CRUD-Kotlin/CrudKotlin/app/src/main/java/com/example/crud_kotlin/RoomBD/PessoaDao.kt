package com.example.crud_kotlin.RoomBD

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
//Comandos "SQL" para realizar o CRUD do projeto
interface PessoaDao {

    @Upsert
    //Insert + Update para caso o campo ja exista ele atualiza
    suspend fun updatePessoa(pessoa: Pessoa)

    @Delete
    //Daletar alguma informação
    suspend fun deletePessoa(pessoa: Pessoa)

    //O Select não é algo que o DAO possui internamente
    // mas é possivel colocar codigos internos utilizando o Query
    @Query("SELECT * FROM pessoa")
    fun getAllPessoa(): Flow<List<Pessoa>>
}