package com.example.foodicsassessment.features.tables.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodicsassessment.R
import com.example.foodicsassessment.core.presentation.ui.theme.colors
import com.example.foodicsassessment.core.presentation.ui.theme.small_text
import com.example.foodicsassessment.features.tables.domain.model.products.Product

@Composable
fun ProductItemComponent(
    modifier: Modifier = Modifier,
    product: Product, onItemClick: () -> Unit,
    cartCount: Int = 0
) {


    Box(modifier = modifier
        .background(color = Color.Transparent)
        .clickable { onItemClick() }
    ) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 12.dp, top = 12.dp)
                .shadow(
                    elevation = 4.dp, shape = RoundedCornerShape(8.dp)
                )
                .background(color = Color.Transparent)
        ) {

            AsyncImage(
                model = product.image.orEmpty(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.img),
                error = painterResource(id = R.drawable.img),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize(),
            )

            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(.6f)
                        .background(
                            color = Color.Unspecified
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(.4f)
                        .background(
                            color = MaterialTheme.colors.backgroundColor
                        )
                        .padding(10.dp),
                ) {
                    Text(text = product.name.orEmpty())
                }
            }
        }


        // Cart Count Circle - Positioned Top-Right
        if (cartCount > 0) {
            Box(
                modifier = Modifier
                    .size(25.dp) // Adjust the size of the circle
                    .align(Alignment.TopEnd)
                    .background(
                        color = Color.Red,
                        shape = CircleShape
                    )
                    .padding(4.dp) // Padding inside the circle
            ) {
                Text(
                    text = cartCount.toString(),
                    color = Color.White,
                    style = MaterialTheme.typography.small_text,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}