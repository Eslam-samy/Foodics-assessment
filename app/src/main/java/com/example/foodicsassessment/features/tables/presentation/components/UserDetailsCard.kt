package com.example.foodicsassessment.features.tables.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodicsassessment.core.presentation.ui.theme.mid_text
import com.example.foodicsassessment.core.presentation.ui.theme.text_button

@Composable
fun UserDetailsBar(
    modifier: Modifier = Modifier, userName: String, userId: String
) {
    Row(
        modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(
            modifier = Modifier.width(10.dp)
        )
        Text(
            text = userName, style = MaterialTheme.typography.mid_text
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Text(
            text = userId, style = MaterialTheme.typography.mid_text
        )
        Spacer(
            modifier = Modifier.width(10.dp)
        )
        Box(
            modifier = Modifier
                .size(15.dp)
                .background(
                    shape = CircleShape,
                    color = Color(0xff23873C)
                )
        )

    }
}


@Preview(showBackground = true)
@Composable
fun UserDetailsBarPreview() {
    UserDetailsBar(
        userName = "Eslam Samy", userId = "99878"
    )
}