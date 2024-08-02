package com.example.firebase_crud

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebase_crud.ui.theme.FirebaseCrudTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.hashMapOf as hashMapOf

class MainActivity : ComponentActivity() {
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseCrudTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(db)
                }
            }
        }
    }
}

@Composable
fun App(db : FirebaseFirestore){
    var nome by remember { mutableStateOf("")  }
    var telefone by remember { mutableStateOf("")  }

    Column(
        Modifier
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            Arrangement.Center

        ) {
            Text(
                text = "App Firebase FireStore",
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.3f)
                    .padding(10.dp)
            ) {
                Text(text = "Nome:",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Cursive)
            }
            Column(

            ) {
                TextField(
                    value = nome,
                    onValueChange = { nome = it } ,
                    label = { Text(text = "Nome:")}

                )
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.3f)
            ) {
                Text(text = "Telefone",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Cursive)
            }
            Column(

            ) {
                TextField(
                    value = telefone,
                    onValueChange = { telefone = it} ,
                    label = { Text(text = "Telefone:")}

                )
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(30.dp),

        ){

        }

        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center

        ) {
            Button(onClick = {

                val city =  hashMapOf(
                    "nome" to nome,
                    "telefone" to telefone
                )

                db.collection("pessoas").document("PrimeiroCliente")
                    .set(city)
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written") }
                    .addOnFailureListener { e-> Log.w(ContentValues.TAG, "Error ", e) }

            }) {
                Text(text = "Cadastrar")

            }

            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ){
            Row(Modifier.fillMaxWidth()) {
                Column(
                    Modifier.fillMaxWidth(0.3f)
                ) {
                    db.collection("pessoas")
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents){
                                val lista = hashMapOf(
                                    "nome" to "${document.data.get("nome")}",
                                    "telefone" to "${document.data.get("telefone")}"
                                )
                                Log.d(TAG, "${document.id} => ${document.data}")
                            }

                        }


                        .addOnFailureListener { exception ->
                            Log.w(TAG, "Error", exception)
                        }
                    


                }
            }

            }
        }
    }
}



