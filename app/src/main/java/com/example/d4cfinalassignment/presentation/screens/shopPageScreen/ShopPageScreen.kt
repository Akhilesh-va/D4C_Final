package com.example.d4cfinalassignment.presentation.screens.shopPageScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


import com.example.d4cfinalassignment.R

import com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp.Bars
import com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp.Categories.Categories
import com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp.HeroProduct.ProductItem
import com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp.NewProduct
import com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp.ShopPageTopBar
import com.example.d4cfinalassignment.presentation.ui.theme.StandardFont
import com.example.d4cfinalassignment.utils.NetworkResult
import androidx.compose.runtime.getValue
import com.example.d4cfinalassignment.viewmodels.ProductViewmodel
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController

@Composable
fun ShopPageScreen(
    navController: NavController,
    viewmodel: ProductViewmodel = hiltViewModel()

) {
    val productResult by viewmodel.productResultView.collectAsState()
    LaunchedEffect(Unit) {
        viewmodel.fetchProducts()
    }
    val categoryList = listOf(
        "Cleaners" to R.drawable.product_image,
        "Groceries" to R.drawable.categorysample,
        "Stationery" to R.drawable.product_image,
        "Laundry" to R.drawable.categorysample,
        "Beverages" to R.drawable.product_image,
        "Groceries" to R.drawable.categorysample,
        "Stationery" to R.drawable.product_image,
        "Laundry" to R.drawable.categorysample,
        "Beverages" to R.drawable.product_image
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        item {
            ShopPageTopBar()
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)

            ) {
                Image(painter = painterResource(R.drawable.shopflowcard1), "")
                Column(modifier = Modifier.padding(start = 44.dp, top = 36.dp)) {
                    Text(
                        text = "GET 20% OFF",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        text = "GET 20% OFF",
                        fontFamily = StandardFont,
                        modifier = Modifier.padding(top = 16.dp, bottom = 24.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                    Text(
                        text = "12-16 October",
                        fontFamily = StandardFont,
                        modifier = Modifier
                            .clip(RoundedCornerShape(200.dp))
                            .background(color = colorResource(R.color.d4cgreen))
                            .padding(horizontal = 8.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray
                    )
                }
                Icon(
                    painter = painterResource(R.drawable.sunset),
                    " ",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(bottom = 54.dp, end = 76.dp)
                        .size(54.dp)
                        .align(Alignment.BottomEnd)
                )
                Bars(
                    modifier = Modifier
                        .padding(start = 55.dp)
                        .align(Alignment.BottomStart)
                        .width(130.dp)
                )
            }
        }

        item {
            Categories(categoryList)
        }

        item {
            NewProduct()
        }


        item {
            Button(onClick = {
                navController.navigate("ticket_screen")


            }) {
                Text("Raise Ticket")
            }
        }


    }}

