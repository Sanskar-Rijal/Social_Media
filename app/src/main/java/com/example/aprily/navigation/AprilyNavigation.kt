package com.example.aprily.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//import com.example.aprily.screen.auth.LoginScreen
import com.example.aprily.screen.auth.authViewModel

@Composable
fun AprilyNavigation(authViewModel: authViewModel= hiltViewModel()){

    val navController = rememberNavController()

    //now making a Navhost
    NavHost(navController=navController,
        startDestination = AprilyScreen.LoginScreen.name) {

        //making navgraphs
        composable(AprilyScreen.LoginScreen.name){
            //LoginScreen(navController,authViewModel)
        }
    }
}