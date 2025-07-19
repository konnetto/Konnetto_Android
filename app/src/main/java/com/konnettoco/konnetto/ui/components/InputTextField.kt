package com.konnettoco.konnetto.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konnettoco.konnetto.ui.theme.KonnettoTheme


//@Composable
//fun InputTextField(
//    input: String,
//    onValueChange: (String) -> Unit,
//    keyboardType: KeyboardType,
//    modifier: Modifier = Modifier,
//    hint: String,
//    prefix: (@Composable () -> Unit)? = null,
//    suffix: (@Composable () -> Unit)? = null
//) {
//    val gradientColors = listOf(Color.Gray, Color.LightGray)
//    val brush = Brush.linearGradient(gradientColors)
//
//    Box(
//        modifier = modifier
//            .background(Color.White, shape = RoundedCornerShape(30.dp))
//            .border(BorderStroke(2.dp, brush), shape = RoundedCornerShape(30.dp))
//            .padding(2.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(horizontal = 16.dp, vertical = 12.dp)
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // Menampilkan prefix jika ada
//            prefix?.invoke()
//
//            BasicTextField(
//                value = input,
//                onValueChange = onValueChange,
//                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
//                modifier = Modifier
//                    .weight(1f) // Memberikan ruang yang fleksibel untuk teks
//                    .padding(10.dp),
//                decorationBox = { innerTextField ->
//                    if (input.isEmpty()) {
//                        Text(
//                            text = hint,
//                            style = TextStyle(color = Color.Gray, fontSize = 16.sp)
//                        )
//                    }
//                    innerTextField()
//                }
//            )
//
//            // Menampilkan suffix jika ada
//            suffix?.invoke()
//        }
//    }
//}

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    input: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    leadingIcon: ImageVector? = null,
    trailingIcon: Painter? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        modifier = modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth(),
        value = input,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        leadingIcon = {if (leadingIcon != null) Icon(imageVector = leadingIcon, null)},
        trailingIcon = {if (trailingIcon != null) Icon(
            painter = trailingIcon,
            contentDescription = null,
            modifier = Modifier.clickable { onTrailingIconClick?.invoke() }
        )},
        singleLine = true,
        keyboardOptions =  KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
//        shape = Shape.large,
        shape = RoundedCornerShape(12.dp),
    )
}

@Preview(showBackground = true)
@Composable
private fun InputTextFieldPreview() {
    KonnettoTheme {
        InputTextField(
            input = "",
            onValueChange = {},
            labelText = "Email",
            keyboardType = KeyboardType.Text
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun InputTextFieldPrev() {
//    KonnettoTheme {
//        InputTextField(
//            input = "",
//            onValueChange = {},
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth(),
//            prefix = {
//                Icon(
//                    painter = painterResource(R.drawable.baseline_email_24),
//                    contentDescription = "Search Icon",
//                    tint = Color.Gray,
//                    modifier = Modifier.size(20.dp)
//                )
//            },
//            hint = "Masukkan Email",
//            keyboardType = KeyboardType.Email
//        )
//    }
//}