package com.example.foodicsassessment.features.tables.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodicsassessment.R
import com.example.foodicsassessment.core.presentation.ui.theme.body_text
import com.example.foodicsassessment.core.presentation.ui.theme.colors
import com.example.foodicsassessment.core.presentation.ui.theme.spacing
import com.example.foodicsassessment.core.presentation.ui.theme.text_button

@SuppressLint("DefaultLocale")
@Composable
fun ViewOrderComponent(
    modifier: Modifier = Modifier,
    totalPrice: Double,
    totalCounter: Int,
    onCheckoutClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                color = Color(0xff410096),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = MaterialTheme.spacing.medium),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = MaterialTheme.colors.backgroundColor,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = totalCounter.toString(),
                    style = MaterialTheme.typography.text_button.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff410096)
                    )
                )


            }
            Spacer(
                modifier = Modifier
                    .width(MaterialTheme.spacing.medium)
            )

            Text(
                text = stringResource(id = R.string.view_order),
                style = MaterialTheme.typography.body_text.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.backgroundColor
                )
            )
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )

            Text(
                text = "Sar ${String.format("%.2f", totalPrice)}",
                style = MaterialTheme.typography.body_text.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.backgroundColor
                )
            )
            Spacer(
                modifier = Modifier
                    .width(MaterialTheme.spacing.medium)
            )
            Icon(
                modifier = Modifier.clickable {
                    onCheckoutClick()
                },
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colors.backgroundColor
            )
        }
    }
}

@Preview
@Composable
fun ViewOrderComponentPreview() {
    ViewOrderComponent(totalPrice = 100.0, totalCounter = 2)
}