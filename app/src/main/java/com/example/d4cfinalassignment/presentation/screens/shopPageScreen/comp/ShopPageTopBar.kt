package com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.d4cfinalassignment.R
import androidx.compose.runtime.getValue
import com.example.d4cfinalassignment.presentation.ui.theme.ScreenHeading
import com.example.d4cfinalassignment.viewmodels.ProductViewmodel

@Composable
fun ShopPageTopBar() {

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 36.dp, start = 16.dp, end = 16.dp) ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row() {
            Icon(Icons.Default.ArrowBack, "", tint = Color.White, modifier = Modifier.size(28.dp))
            Text(text = "Shop", fontFamily = ScreenHeading, fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 10.dp))

        }

        Row(verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.Center) {
            Icon(Icons.Default.Search, "", tint = Color.White, modifier = Modifier.size(28.dp))
            IconWithBadge(R.drawable.heart, 5)
            IconWithBadge(R.drawable.shopping, 3)

        }

    }

}

@Preview()
@Composable
fun ShopPageTopBarPreview() {
    ShopPageTopBar()

}