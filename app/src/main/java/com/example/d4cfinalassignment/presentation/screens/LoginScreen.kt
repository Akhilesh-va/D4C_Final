package com.example.d4cfinalassignment.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.d4cfinalassignment.viewmodels.AuthViewModel
import com.example.d4cfinalassignment.data.models.sendOtpModels.OtpRequest
import com.example.d4cfinalassignment.utils.NetworkResult

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    var countryCode by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    val otpResult by authViewModel.otpResult.collectAsState()

    LaunchedEffect(otpResult) {
        if (otpResult is NetworkResult.Success) {
            val response = (otpResult as NetworkResult.Success).data
            if (response?.statusCode == 200) {
                navController.navigate("otp_verification_screen"  + "/$countryCode" + "/$phoneNumber")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome Back User",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = countryCode,
                onValueChange = { countryCode = it },
                label = { Text("Code") },
                placeholder = { Text("91") },
                modifier = Modifier.weight(0.2f)
            )

            Spacer(modifier = Modifier.width(4.dp))

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") },
                placeholder = { Text("XXXXXXXXXX") },
                modifier = Modifier.weight(0.8f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (otpResult is NetworkResult.Loading) {
            CircularProgressIndicator()
        } else {
            Button(onClick = {
                authViewModel.loginUser(OtpRequest(phoneNumber, countryCode))
            }) {
                Text("Send OTP", fontSize = 18.sp)
            }
        }

        if (otpResult is NetworkResult.Error) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = (otpResult as NetworkResult.Error).message ?: "Something went wrong",
                color = Color.Red
            )
        }
    }
}
