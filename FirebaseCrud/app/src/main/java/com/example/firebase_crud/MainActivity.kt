package com.example.firebase_crud

import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.test.espresso.base.Default
import com.example.firebase_crud.ui.theme.FirebaseCrudTheme
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseCrudTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App(){
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
                    value = "",
                    onValueChange = {} ,
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
                    value = "",
                    onValueChange = {} ,
                    ,
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
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Cadastrar")
            }
        }
    }
}

@Composable
@Preview
fun AppPreview(){
    FirebaseCrudTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            App()
        }
    }
}

