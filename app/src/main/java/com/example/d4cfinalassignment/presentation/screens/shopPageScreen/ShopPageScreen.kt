package com.example.d4cfinalassignment.presentation.screens.shopPageScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.d4cfinalassignment.R
import com.example.d4cfinalassignment.data.models.productModels.Product
import com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp.Bars
import com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp.Categories.Categories
import com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp.HeroProduct.ProductItem
import com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp.NewProduct
import com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp.ShopPageTopBar
import com.example.d4cfinalassignment.presentation.ui.theme.ProductHeading
import com.example.d4cfinalassignment.presentation.ui.theme.StandardFont
import com.example.d4cfinalassignment.utils.NetworkResult
import com.example.d4cfinalassignment.viewmodels.ProductViewmodel

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

        }


        when (productResult) {
            is NetworkResult.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                color = colorResource(R.color.d4cgreen)
                            )
                            Text(
                                text = "Loading products...",
                                color = Color.White,
                                modifier = Modifier.padding(top = 16.dp),
                                fontSize = 16.sp,
                                fontFamily = StandardFont
                            )
                        }
                    }
                }
            }

            is NetworkResult.Success -> {
                val products = (productResult as NetworkResult.Success<Product>).data?.data?.filterNotNull() ?: emptyList()

                if (products.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No products available",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontFamily = StandardFont,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {

                    items(products) { product ->
                        ProductItem(product = product)
                    }
                }
            }

            is NetworkResult.Error -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Error loading products",
                                color = Color.Red,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = StandardFont
                            )
                            Text(
                                text = (productResult as NetworkResult.Error<Product>).message ?: "Unknown error occurred",
                                color = Color.White,
                                modifier = Modifier.padding(top = 8.dp),
                                fontSize = 14.sp,
                                fontFamily = StandardFont,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            null -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Initializing...",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = StandardFont
                        )
                    }
                }
            }
        }


        item {
            Button(
                onClick = {
                    navController.navigate("ticket_screen")
                },
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.Black , contentColor = colorResource(R.color.d4cgreen)),
                modifier = Modifier
                    .background(Color.DarkGray)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Raise Ticket" ,color = colorResource(R.color.d4cgreen))
            }
        }
    }
}