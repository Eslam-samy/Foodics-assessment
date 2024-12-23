package com.example.foodicsassessment.core.presentation.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        onClick = { if (!isLoading) onClick() },
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(12.dp), // Rounded corners
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary, // Background color
            contentColor = MaterialTheme.colorScheme.onPrimary // Text color
        ),
        contentPadding = PaddingValues(16.dp) // Padding inside the button
    ) {
        // Box to overlap the text and the loader
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp), // Size of the loader
                    color = MaterialTheme.colorScheme.onPrimary, // Loader color
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = text,
                    style = TextStyle(fontSize = 16.sp) // Text style
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomButton(
            text = "Submit",
            isLoading = isLoading,
            onClick = { isLoading = !isLoading } // Toggle loading state
        )
    }
}
