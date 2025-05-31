package com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.d4cfinalassignment.R


@Composable
fun Bars(modifier: Modifier) {
    Row(
        modifier=modifier
        , verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Box(
            modifier = Modifier
                .width(35.dp)
                .height(10.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = colorResource(R.color.d4cgreen))


        )
        Box(
            modifier = Modifier
                .width(35.dp)
                .height(10.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = colorResource(R.color.black))


        )
        Box(
            modifier = Modifier
                .width(35.dp)
                .height(10.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = colorResource(R.color.black))


        )

    }

}