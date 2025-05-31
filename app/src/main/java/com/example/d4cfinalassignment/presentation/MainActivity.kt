package com.example.d4cfinalassignment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.d4cfinalassignment.viewmodels.AuthViewModel
import com.example.d4cfinalassignment.viewmodels.VerifyOtpViewmodel
import com.example.d4cfinalassignment.presentation.screens.LoginScreen
import com.example.d4cfinalassignment.presentation.screens.OtpVerificationScreen
import com.example.d4cfinalassignment.presentation.screens.shopPageScreen.ShopPageScreen
import com.example.d4cfinalassignment.presentation.screens.trial
import com.example.d4cfinalassignment.presentation.ui.theme.D4CFinalAssignmentTheme
import com.example.d4cfinalassignment.viewmodels.ProductViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel :AuthViewModel = hiltViewModel()
            val verifyOtpViewmodel :VerifyOtpViewmodel = hiltViewModel()
            val productViewmodel: ProductViewmodel = hiltViewModel()
        val navController = rememberNavController()
            NavHost(navController = navController , startDestination = "login_screen" , builder = {
                composable("login_screen") {
                    LoginScreen(navController ,viewModel)
                }
                composable("otp_verification_screen" + "/{countryCode}" + "/{phoneNumber}") {
                    val countryCode = it.arguments?.getString("countryCode")
                    val phoneNumber = it.arguments?.getString("phoneNumber")
                    OtpVerificationScreen(verifyOtpViewmodel , productViewmodel,navController ,  countryCode!! , phoneNumber!!)
                }
                composable("shop_page_screen") {
                    ShopPageScreen()
                }
            })
        }
    }
}
