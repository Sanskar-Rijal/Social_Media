package com.example.aprily

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aprily.navigation.AprilyNavigation
import com.example.aprily.screen.auth.authViewModel
import com.example.aprily.ui.theme.AprilyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            myapp {
               // AprilyApp()
                AprilyNavigation()
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

//@Composable
//fun AprilyApp(){
//    val vm = hiltViewModel<authViewModel>()
//}