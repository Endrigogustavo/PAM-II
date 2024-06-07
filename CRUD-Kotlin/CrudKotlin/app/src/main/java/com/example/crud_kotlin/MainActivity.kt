package com.example.crud_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.crud_kotlin.RoomBD.Pessoa
import com.example.crud_kotlin.RoomBD.PessoaDataBase
import com.example.crud_kotlin.ui.theme.CrudKotlinTheme
import com.example.crud_kotlin.viewModel.PessoaViewModel
import com.example.crud_kotlin.viewModel.Repository

class MainActivity : ComponentActivity() {

    //Criando o banco de dados com base na classe de criação, ainda com o seu nome
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            PessoaDataBase::class.java,
            "pessoa.db"
        ).build()
    }

    //É utilizado para criar a view do Model
    private val viewModel by viewModels<PessoaViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PessoaViewModel(Repository(db)) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrudKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(viewModel, this)
                }
            }
        }
    }
}

@Composable
fun App(viewModel: PessoaViewModel, mainActivity: MainActivity){
    //Variaveis mutaveis para setar os form
    var nome by remember {
        mutableStateOf("")
    }

    var telefone by remember {
        mutableStateOf("")
    }

    //juntando as var
    val pessoa = Pessoa(
        nome,
        telefone
    )

    //Colocando em um vetor
    var pessoaList by remember{
        mutableStateOf(listOf<Pessoa>())
    }

    //
viewModel.getPessoa().observe(mainActivity){
    pessoaList = it
}
    Column(
        Modifier
            .background(Color.White)
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            Text(text = "App Cadastro",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }

        Row(
            Modifier
                .padding(20.dp)
        ) {
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            Arrangement.Center
        ) {
            TextField(
                value = nome,
                onValueChange = { nome = it } ,
                label = { Text(text = "Nome:")}
            )
        }


        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            TextField(
                value = telefone,
                onValueChange = { telefone = it } ,
                label = { Text(text = "Telefone:")}
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            Arrangement.Center
        ) {
            //Botão de cadastrar
         Button(onClick = {
             viewModel.upsertPessoa(pessoa)
         }) {
             Text(text = "Cadastrar")
         }

        }

        Divider()
        LazyColumn {
            //SELECT da aplicação
            items(pessoaList){pessoa ->
                Row(
                    Modifier
                        .clickable {
                            viewModel.deletePessoa(pessoa)
                        }
                        .fillMaxWidth(),
                    Arrangement.Center
                ){
                    Column(
                        Modifier
                            .fillMaxWidth(0.5f)
                    ) {
                        Text(text = "${pessoa.nome}")
                    }

                    Column(
                        Modifier
                            .fillMaxWidth(0.5f)
                    ) {
                        Text(text = "${pessoa.telefone}")
                    }

                }
Divider()

            }
        }
    }

}


