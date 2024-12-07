package com.example.aprily.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aprily.components.NotificationMessage
import com.example.aprily.screen.auth.SignupScreen
//import com.example.aprily.screen.auth.LoginScreen
import com.example.aprily.screen.auth.authViewModel

@Composable
fun AprilyNavigation(authViewModel: authViewModel= hiltViewModel()){

    val navController = rememberNavController()

    NotificationMessage(authViewModel)

    //now making a Navhost
    NavHost(navController=navController,
        startDestination = AprilyScreen.SignupScreen.name) {

        //making navgraphs
        composable(AprilyScreen.SignupScreen.name){
            SignupScreen(navController,authViewModel)
        }
    }
}