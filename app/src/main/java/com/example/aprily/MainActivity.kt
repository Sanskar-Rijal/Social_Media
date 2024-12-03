package com.example.aprily

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.aprily.ui.theme.AprilyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            myapp {

            }
        }
    }
}

@Composable
fun myapp(content:@Composable ()-> Unit){
    AprilyTheme {
        content()
    }
}