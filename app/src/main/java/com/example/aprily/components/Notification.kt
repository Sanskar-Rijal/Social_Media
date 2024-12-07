package com.example.aprily.components

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.aprily.screen.auth.authViewModel

@Composable
fun NotificationMessage(viewModel: authViewModel){
    val notificationState=viewModel.popupNotification.value
    val notifMessage = notificationState?.getContentorNull()

    //to display toast message we need context
    val contex:Context = LocalContext.current
    if(notifMessage !=null){
        Toast.makeText(contex, notifMessage, Toast.LENGTH_SHORT).show()
    }

}