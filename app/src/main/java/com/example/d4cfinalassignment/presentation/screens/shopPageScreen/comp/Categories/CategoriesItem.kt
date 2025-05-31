package com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp.Categories


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.d4cfinalassignment.R

@Composable
fun CategoriesItem(name:String , image:Int) {
    Column(verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally ,
        modifier = Modifier.padding(start = 6.dp)){
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(70.dp)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(image),
                "",
                modifier = Modifier.size(60.dp)
            )


        }
        Text(text = name, fontSize = 12.sp , color = Color.White)


    }


}

@Preview(showBackground = true , showSystemUi = true)
@Composable
private fun CategoriesItemPreview() {
    CategoriesItem("Cleaners",R.drawable.categorysample )

}