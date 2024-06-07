package com.example.crud_kotlin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.crud_kotlin.RoomBD.Pessoa
import kotlinx.coroutines.launch

class PessoaViewModel(private val repository: Repository): ViewModel() {
    //Com o AsLiveData é possivel visualizar em tempo real as requisições
    fun getPessoa() = repository.getAllPessoa().asLiveData(viewModelScope.coroutineContext)

    //Comandos CRUD
    fun upsertPessoa(pessoa: Pessoa) {
        viewModelScope.launch {
            repository.upsertPessoa(pessoa)
        }
    }

    fun deletePessoa(pessoa: Pessoa) {
        viewModelScope.launch {
            repository.deletePessoa(pessoa)
        }
    }
}