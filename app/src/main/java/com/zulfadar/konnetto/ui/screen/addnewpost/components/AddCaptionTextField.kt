package com.zulfadar.konnetto.ui.screen.addnewpost.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddCaptionTextField(
    modifier: Modifier = Modifier,
    caption: String,
) {
    var captionEdt by remember { mutableStateOf(caption) }
    var maxChar = 500

    OutlinedTextField(
        value = captionEdt,
        onValueChange = { newCaption ->
            if (newCaption.length <= maxChar) {
                captionEdt = newCaption
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(150.dp), // minimal tinggi
        placeholder = {
            Text(text = "Share your thoughts about anime, manga, cosplay, etc...", color = Color.LightGray)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        ),
        shape = RoundedCornerShape(12.dp),
        maxLines = 10,
        textStyle = MaterialTheme.typography.bodyLarge,
    )
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = modifier.padding(12.dp),
            text = "Express yourself freely",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        Text(
            text = "${captionEdt.length}/$maxChar",
            modifier = Modifier
                .padding(end = 8.dp),
            style = MaterialTheme.typography.bodySmall,
            color = if (captionEdt.length >= maxChar) Color.Red else Color.Gray
        )
    }
}