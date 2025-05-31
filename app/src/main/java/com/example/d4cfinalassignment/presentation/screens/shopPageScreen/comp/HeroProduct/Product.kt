package com.example.d4cfinalassignment.presentation.screens.shopPageScreen.comp.HeroProduct


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.d4cfinalassignment.R
import com.example.d4cfinalassignment.data.models.productModels.ProductData

import com.example.d4cfinalassignment.presentation.ui.theme.ProductHeading
import com.example.d4cfinalassignment.presentation.ui.theme.StandardFont


@Composable
fun ProductItem(product: ProductData) {

    val primaryImageUrl = product.images?.find { it?.primary == true }?.url


    Box(modifier = Modifier
        .height(550.dp)
        .padding(top = 20.dp), contentAlignment = Alignment.Center) {
        Column {
            Box() {
                Image(
                    painter = painterResource(R.drawable.card_grey_bg_png), "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(550.dp)


                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {

                    Image(
                        painter = if(product.isLiked ==true){
                            painterResource(R.drawable.love)

                        }
                        else
                        {
                            painterResource(R.drawable.unselectedheart)
                        },
                        "",
                        modifier = Modifier
                            .padding(top = 8.dp, start = 8.dp)
                            .clip(shape = CircleShape)
                            .background(Color.Black)
                            .size(40.dp)
                            .padding(8.dp)


                    )
                    if(product.isBestSeller == true) {
                        Text(
                            text = "Best seller",
                            modifier = Modifier
                                .padding(end = 40.dp)
                                .clip(
                                    RoundedCornerShape(200.dp)
                                )
                                .background(Color.Black)
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = colorResource(R.color.d4cgreen)
                        )
                    }

                }
                AsyncImage(
                    model = primaryImageUrl ?: R.drawable.product_image,
                    contentDescription = product.title,
                    modifier = Modifier
                        .size(280.dp)
                        .offset(x=50.dp,y = 65.dp),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painterResource(R.drawable.card_black_shape), "",
                    modifier = Modifier
                        .width(2800.dp)
                        .height(350.dp)
                        .padding(30.dp)
                        .offset(y = 260.dp)
                )

                Image(
                    painter = painterResource(R.drawable.cart3), "",
                    modifier = Modifier
                        .offset(y = 460.dp, x = 315.dp)
                        .padding(3.dp)
                        .size(60.dp)
                        .clip(CircleShape)
                        .border(
                            2.dp, color = colorResource(R.color.white),
                            CircleShape
                        )
                        .padding(12.dp)
                )

                Row(
                    modifier = Modifier
                        .offset(y = 355.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = product.title?:"Product", color = colorResource(R.color.d4cgreen), fontFamily = ProductHeading,
                        fontSize = 36.sp, modifier = Modifier.padding(top = 5.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 35.dp)

                    ) {


                        if(product.isInCart==true) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(colorResource(R.color.d4cgreen))
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "In stock",
                                fontFamily = StandardFont,
                                color = colorResource(R.color.d4cgreen),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                            )
                        }
                        else{
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color.Red)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "Out of stock",
                                fontFamily = StandardFont,
                                color = Color.Red,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Column(
                    modifier = Modifier
                        .offset(y = 395.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        product.subTitle?:"",
                        fontFamily = StandardFont,
                        color = Color.White, fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            product.subTitle2?:"",
                            fontFamily = StandardFont,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(colorResource(R.color.white))
                        )
                        Spacer(modifier = Modifier.width(4.dp))

                    }
                    Spacer(modifier = Modifier.height(2.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = product.mrp.toString(), color = colorResource(R.color.teal_700),
                            fontFamily = StandardFont,
                            fontSize = 16.sp, fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = product.price.toString(), color = Color.LightGray,
                            fontFamily = StandardFont,
                            fontSize = 14.sp, fontWeight = FontWeight.Normal,
                            style = TextStyle(textDecoration = TextDecoration.LineThrough)
                        )

                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(product.reviewsRating?:0) {
                            Image(
                                painterResource(R.drawable.star),
                                "",
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(start = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = product.reviewsCount.toString() , color = Color.White ,
                            fontFamily = StandardFont,
                            textDecoration = TextDecoration.Underline ,)
                    }

                }


            }


        }
    }


}