package com.example.crud_firebase_kotlin

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crud_firebase_kotlin.ui.theme.CRUDFirebaseKotlinTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CRUDFirebaseKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        Modifier

                            .fillMaxWidth(),
                        Arrangement.Center,
                        Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))
                        List(info = Form())
                    }
                   
                }
            }
        }
    }
}

@Composable
fun Form(): HashMap<String, String> {
    val db = Firebase.firestore
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }


    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "App Firebase FireStore",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF006699)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it} ,
            label = { Text(text = "Nome:")},
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Person Icon"
                )
            },
            modifier = Modifier.wrapContentWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = telefone,
            onValueChange = { telefone = it },
            label = { Text("Telefone:") },
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "Person Icon"
                )
            },
            modifier = Modifier.wrapContentWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.wrapContentWidth(),
            onClick = {

                val city = hashMapOf(
                    "nome" to nome,
                    "telefone" to telefone
                )

                db.collection("pessoas")
                    .add(city)
                    .addOnSuccessListener {
                        Log.d(
                            ContentValues.TAG,
                            "DocumentSnapshot successfully written"
                        )
                    }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error ", e) }

            }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Person Icon"
            )
            Text(text = "Cadastrar")

        }
    }

    val info = hashMapOf(
        "nome" to nome,
        "telefone" to telefone
    )
    return info
}

data class Pessoa(val nome: String = "", val telefone: String = "", val id: String = "")

@Composable
fun List(info: HashMap<String, String>) {
    val db = Firebase.firestore
    var pessoas by remember { mutableStateOf<List<Pessoa>>(emptyList()) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(pessoas) { pessoa ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),

                    ) {

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Build,
                                contentDescription = "Person icon",
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = pessoa.nome,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = pessoa.telefone,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )

                            }
                            Row(
                                Modifier
                                    .fillMaxWidth(),
                                Arrangement.End
                            ) {
                                Button(
                                    onClick = {

                                        db.collection("pessoas").document(pessoa.id)
                                            .delete()
                                            .addOnSuccessListener {
                                                Log.d(
                                                    ContentValues.TAG,
                                                    "DocumentSnapshot successfully written"
                                                )
                                            }
                                            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error ", e) }

                                    }) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = "Person Icon"
                                    )

                                }

                                Button(onClick = {

                                    db.collection("pessoas").document(pessoa.id)
                                        .update(info as Map<String, Any>)
                                        .addOnSuccessListener {
                                            Log.d(
                                                ContentValues.TAG,
                                                "DocumentSnapshot successfully written"
                                            )
                                        }
                                        .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error ", e) }

                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Edit,
                                        contentDescription = "Person Icon"
                                    )

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        db.collection("pessoas")
            .get()
            .addOnSuccessListener { documents ->
                val listaPessoas = mutableListOf<Pessoa>()
                for (document in documents) {

                    val pessoa = Pessoa(
                        id = document.id,
                        nome = document.data["nome"] as String? ?: "",
                        telefone = document.data["telefone"] as String? ?: ""
                    )
                    Log.w(TAG, "Sucesso ao acessar o documento: $pessoa")
                    listaPessoas.add(pessoa)
                }
                pessoas = listaPessoas
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }
}

