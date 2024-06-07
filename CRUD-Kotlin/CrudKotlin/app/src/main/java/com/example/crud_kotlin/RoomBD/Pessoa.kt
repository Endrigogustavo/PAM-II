package com.example.crud_kotlin.RoomBD

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
//Criação dos campos do BD utilizando os campos e seu tipo em sequencia
data class Pessoa(
    //Variaveis que estarão no banco de dados
    val nome: String,
    val telefone: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
