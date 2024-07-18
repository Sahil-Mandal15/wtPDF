package com.example.wtpdf.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.wtpdf.R
import com.example.wtpdf.presentation.viewmodel.RegisterViewModel

@Preview(showBackground = true)
@Composable
fun RegisterScreen(
    RegisterViewModel: RegisterViewModel = hiltViewModel(),
    navController: NavHostController
) {

    Box (modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.signup_logo), contentDescription = "sign up logo")
    }
}