package com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp.Categories


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.d4cfinalassignment.presentation.ui.theme.ScreenHeading


@Composable
fun Categories(categories : List<Pair<String,Int>>) {

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Row( modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.SpaceBetween ) {
            Text(
                text = "Categories", fontSize = 24.sp, fontFamily = ScreenHeading, fontWeight = FontWeight.ExtraBold , color = Color.White
            )
            Text(
                text = "See all",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                color = Color.White
            )
        }
        LazyRow(){
            items(categories) { i->
                CategoriesItem(i.first,i.second)
                // it would be better If i will do it with data model but as it is staic I thing I can go with this

            }

        }


    }

}

