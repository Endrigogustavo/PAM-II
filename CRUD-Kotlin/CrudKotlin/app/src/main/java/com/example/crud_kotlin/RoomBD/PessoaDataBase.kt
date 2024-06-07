package com.example.crud_kotlin.RoomBD

import androidx.room.Database
import androidx.room.RoomDatabase

//Define o que vai ser o banco de dados do projeto
//As entidades estão definidas diretamente do Pessoa, onde esta instanciando a classe
@Database(
    entities = [Pessoa::class],
    version = 1
)

//Comandos CRUD
abstract class PessoaDataBase: RoomDatabase() {
    abstract fun pessoaDao(): PessoaDao
}

