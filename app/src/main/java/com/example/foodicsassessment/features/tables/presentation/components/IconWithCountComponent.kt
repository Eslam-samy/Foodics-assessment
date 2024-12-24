package com.example.foodicsassessment.features.tables.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.foodicsassessment.core.presentation.ui.theme.mid_text
import com.example.foodicsassessment.core.presentation.ui.theme.spacing

@Composable
fun IconWithCount(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    count: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(MaterialTheme.spacing.normal),
            imageVector = icon,
            contentDescription = null
        )
        Spacer(
            modifier = Modifier
                .width(10.dp)
        )
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.mid_text
        )
    }
}