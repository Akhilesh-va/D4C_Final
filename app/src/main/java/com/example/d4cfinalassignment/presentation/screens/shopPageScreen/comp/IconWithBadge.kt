package com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.d4cfinalassignment.R

@Composable
fun IconWithBadge(
    image: Int,
    count: Int = 5
) {
    Box() {
        Icon(
            painter = painterResource(image), "", tint = Color.White,
            modifier = Modifier
                .size(36.dp)
                .padding(start = 10.dp)
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 2.dp, start =25.dp)
                .size(18.dp)
                .clip(CircleShape)
                .background(color = colorResource(R.color.d4cgreen))
            ,contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text =count.toString(),
                color = Color.Black,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun preview() {
    IconWithBadge(R.drawable.heart,6)

}
