package com.example.wtpdf.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.wtpdf.R
import com.example.wtpdf.presentation.components.CustomOutlinedTF
import com.example.wtpdf.presentation.viewmodel.RegisterViewModel


@Composable
fun RegisterScreen(
    RegisterViewModel: RegisterViewModel = hiltViewModel(),
    navController: NavHostController
) {

   // val homeViewModel: HomeViewModel = hiltViewModel()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Box (modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.signup_logo), contentDescription = "sign up logo")
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CustomOutlinedTF(
                value = email,
                onValueChange = { email = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                label = { Text(text = "Email Address")})

            Spacer(modifier = Modifier.height(height = 7.dp))

            CustomOutlinedTF(
                value = password,
                onValueChange = { password = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                label = { Text(text = "Password") },
                applyVisualTransformation = true
                )


            Spacer(modifier = Modifier.height(height = 21.dp))

            Button(onClick = {
                RegisterViewModel.registerUser(email = email, password = password)
                //homeViewModel.getUserDetails()
             //   navController.navigate(Routes.Home.name)
            }) {

            }
        }
    }
}