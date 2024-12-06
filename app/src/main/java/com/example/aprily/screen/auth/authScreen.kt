package com.example.aprily.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.contentReceiver
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import com.example.aprily.R


@Composable
fun SignupScreen(navController: NavController,authvm:authViewModel) {
    //we will have 3 states
    val usernameState = remember {
        mutableStateOf("")
    }
    val emailState = remember {
        mutableStateOf("")
    }
    val PasswordState = remember {
        mutableStateOf("")
    }

    /**
     * checking whether the input inside
     * text field are empty or not by making a state
     */
    val valid =
        remember(usernameState.value) { //we are checking whether usernameState.value is empty or not
            usernameState.value.trim()
                .isNotEmpty() //if it's not empty then it will return true
        }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(it)
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val aprilyIcon: Painter = if(isSystemInDarkTheme()) painterResource(R.drawable.aprilywhite) else
                painterResource(R.drawable.aprily)
            Image(
                painter = aprilyIcon,
                contentDescription = "APRILY",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(8.dp)
                    .width(250.dp)
            )
            Column(modifier = Modifier.padding(top = 50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Signup",
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontSize = 30.sp,
                    fontFamily = FontFamily.SansSerif
                )

                TextField(
                    valueState = usernameState,
                    placeholder = "Username"
                )
                TextField(
                    valueState = emailState,
                    placeholder = "Email"
                )
                TextField(
                    valueState = PasswordState,
                    placeholder = "Password",
                    visualTransformation = PasswordVisualTransformation(),
                    imeAction = ImeAction.Done
                )

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                             authvm.onSignup(
                                 usernameState.value,
                                 emailState.value,
                                 PasswordState.value
                             )
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(
                            text = "SIGN UP",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Text(
                    text = "Already a user? Go to Login ->",
                    color = Color.LightGray,
                    modifier = Modifier.padding(8.dp)
                        .clickable { },
                    fontSize = 15.sp
                )
            }
        }
    }
}


@Composable
fun TextField(
    valueState:MutableState<String>,
    placeholder:String,
    keyboardType: KeyboardType= KeyboardType.Text,
    imeAction: ImeAction=ImeAction.Next,
    onAction:KeyboardActions=KeyboardActions.Default,
    visualTransformation: VisualTransformation=VisualTransformation.None
){

    OutlinedTextField(value =valueState.value,
        onValueChange = {newval->
            valueState.value=newval
        },
        label = {
            Text(text = placeholder)
        },
        maxLines = 1,
        singleLine = true,
        keyboardActions = onAction,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType,
            imeAction = imeAction),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
        visualTransformation = visualTransformation,
    )

}