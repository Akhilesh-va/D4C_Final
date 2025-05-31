package com.example.d4cfinalassignment.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.d4cfinalassignment.viewmodels.VerifyOtpViewmodel
import com.example.d4cfinalassignment.data.models.sendOtpModels.OtpRequest
import com.example.d4cfinalassignment.data.models.verifyOtpModels.VerifyOtpRequest
import com.example.d4cfinalassignment.utils.NetworkResult
import com.example.d4cfinalassignment.utils.TokenManager
import com.example.d4cfinalassignment.viewmodels.ProductViewmodel


@Composable
fun OtpVerificationScreen(
    verifyOtpViewmodel: VerifyOtpViewmodel,
    productViewmodel: ProductViewmodel,
    navController: NavController,
    countryCode: String, phoneNumber: String
) {
    val context = LocalContext.current
    val verifyOtpResult by verifyOtpViewmodel.verifyOtpResult.collectAsState()
    val tokenManager = remember { TokenManager(context) }
    var otpValue by remember { mutableStateOf("") }

    LaunchedEffect(verifyOtpResult) {
        if (verifyOtpResult is NetworkResult.Success) {
            val response = (verifyOtpResult as NetworkResult.Success).data
            if (response?.statusCode == 200) {
                navController.navigate("shop_page_screen")
                val accessToken = response.data?.jwt
                val refreshToken = response.data?.refreshToken
                Log.d("accessToken", accessToken.toString())
                Log.d("refreshToken", refreshToken.toString())
                if (accessToken != null && refreshToken != null) {
                    tokenManager.saveTokens(accessToken, refreshToken)
                    navController.navigate("shop_page_screen") {
                        popUpTo("otp_verification_screen") { inclusive = true }
                }
            }
        }
    }
 }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(
            text = "User details",
            fontSize = 36.sp, fontWeight = FontWeight.Bold
        )

        Row() {
            Text("+")
            Text(countryCode)
            Spacer(modifier = Modifier.width(2.dp))
            Spacer(modifier = Modifier.width(2.dp))
            Text(phoneNumber)


        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = otpValue,
            onValueChange = {

                otpValue = it

            },
            label = { Text("Enter OTP") },
            placeholder = { Text("123456") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
        )
        Spacer(modifier = Modifier.height(24.dp))
        if (verifyOtpResult is NetworkResult.Loading) {
            CircularProgressIndicator()
        } else {
            Button(onClick = {
                productViewmodel.fetchProducts()
               verifyOtpViewmodel.verifyOtp(VerifyOtpRequest(phoneNumber, countryCode,otpValue))
                otpValue=""

            }) {
                Text("Verify OTP", fontSize = 18.sp)
            }
        }

        if (verifyOtpResult is NetworkResult.Error) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = (verifyOtpResult as NetworkResult.Error).message ?: "Something went wrong",
                color = Color.Red
            )
        }
    }


}