package com.example.foodicsassessment.core.presentation.components
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    isPassword: Boolean = false,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    // Focus state
    var isFocused by remember { mutableStateOf(false) }

    // FocusRequester to manage focus
    val focusRequester = FocusRequester()

    // Animations for border and background colors
    val borderColor by animateColorAsState(
        targetValue = when {
            isFocused -> MaterialTheme.colorScheme.primary // Primary color when focused
            isError -> MaterialTheme.colorScheme.error // com.example.foodicsassessment.core.domain.util.Error color when error
            else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f) // Default border color (gray)
        }
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isError) MaterialTheme.colorScheme.error.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface,
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                label,
                color = if (isFocused || value.isNotEmpty()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.6f
                )
            )
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = when {
                isPassword -> KeyboardType.Password
                else -> KeyboardType.Text
            }
        ),
        colors = TextFieldDefaults.colors().copy(
            focusedIndicatorColor = Color.Transparent, // No focused indicator
            unfocusedIndicatorColor = Color.Transparent, // No unfocused indicator
        ),
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurface // Text color based on surface
        ),
        shape = RoundedCornerShape(8.dp), // Rounded corners
        isError = isError, // Pass the error state
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                backgroundColor,
                shape = RoundedCornerShape(8.dp)
            ) // Background color with rounded corners
            .border(
                2.dp,
                borderColor,
                RoundedCornerShape(8.dp)
            ) // Border color with rounded corners
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused // Update focus state
            }
            .focusRequester(focusRequester) // Focus management
    )
}

@Preview(showBackground = true)
@Composable
fun CommonTextFieldPreview() {
    var value by remember { mutableStateOf("password123") }
    var isError by remember { mutableStateOf(false) }
    CommonTextField(
        value = value,
        onValueChange = { value = it },
        label = "Password",
        isPassword = true,
        isError = isError
    )
}
