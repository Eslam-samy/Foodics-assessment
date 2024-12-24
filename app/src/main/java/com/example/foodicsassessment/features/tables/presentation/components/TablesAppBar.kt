package com.example.foodicsassessment.features.tables.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodicsassessment.R
import com.example.foodicsassessment.core.presentation.ui.theme.colors
import com.example.foodicsassessment.core.presentation.ui.theme.spacing

@Composable
fun TablesAppBar(
    modifier: Modifier = Modifier,
    tablesNumber: Int,
    usersNumber: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colors.textColor
        )
        Spacer(
            modifier = Modifier
                .width(MaterialTheme.spacing.medium)
        )
        Text(
            text = stringResource(id = R.string.menu),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        IconWithCount(icon = Icons.Default.Restaurant, count = tablesNumber)
        Spacer(
            modifier = Modifier
                .width(MaterialTheme.spacing.medium)
        )
        IconWithCount(icon = Icons.Default.People, count = usersNumber)
        Spacer(
            modifier = Modifier
                .width(MaterialTheme.spacing.medium)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun TablesAppBarPreview() {
    TablesAppBar(
        tablesNumber = 10,
        usersNumber = 5
    )
}