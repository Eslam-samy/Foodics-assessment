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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.foodicsassessment.core.presentation.ui.theme.body_text
import com.example.foodicsassessment.core.presentation.ui.theme.colors
import com.example.foodicsassessment.core.presentation.ui.theme.mid_text
import com.example.foodicsassessment.core.presentation.ui.theme.small_text
import com.example.foodicsassessment.core.presentation.ui.theme.text_button

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolder: String,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    prefixIcon: ImageVector? = null
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = FocusRequester()

    val borderColor by animateColorAsState(
        targetValue = when {
            isFocused -> MaterialTheme.colorScheme.primary
            isError -> MaterialTheme.colorScheme.error
            else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        }
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isError) MaterialTheme.colorScheme.error.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface,
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
            .border(1.dp, borderColor.copy(alpha = .3f), RoundedCornerShape(8.dp))
            .onFocusChanged { isFocused = it.isFocused }
            .focusRequester(focusRequester)
            .padding(horizontal = 8.dp)  // Custom padding around the whole text field
    ) {
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.backgroundColor)
        ) {
            if (prefixIcon != null) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = prefixIcon,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = MaterialTheme.typography.small_text.copy(
                    color = MaterialTheme.colors.textColor
                ),
                singleLine = true,
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = placeHolder,
                            style = MaterialTheme.typography.mid_text.copy(
                                color = MaterialTheme.colors.textColor.copy(alpha = .3f)
                            )
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommonTextFieldPreview() {
    var value by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    CommonTextField(
        value = value,
        onValueChange = { value = it },
        isError = isError,
        placeHolder = "Search",
        prefixIcon = Icons.Default.Search
    )
}
