package com.konnettoco.konnetto.ui.screen.profile.editprofilescreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun EditBioTextField(
    modifier: Modifier = Modifier,
    bio: String,
) {
    var bioEdt by remember { mutableStateOf(bio) }
    var maxChar = 150

    Column(
        modifier = modifier.padding(vertical = 12.dp)
    ) {
        OutlinedTextField(
            value = bioEdt,
            onValueChange = { newBio ->
                if (newBio.length <= maxChar) {
                    bioEdt = newBio
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .height(80.dp), // minimal tinggi
            placeholder = {
                Text(
                    text = "Share your thoughts about anime, manga, cosplay, etc...",
                    color = Color.LightGray
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "displayname Icon",
                    tint = Color.LightGray
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
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
                text = "${bioEdt.length}/$maxChar",
                modifier = Modifier
                    .padding(end = 8.dp),
                style = MaterialTheme.typography.bodySmall,
                color = if (bioEdt.length >= maxChar) Color.Red else Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditBioTextFieldPrev() {
    KonnettoTheme {
        EditBioTextField(
            bio = "asasf asdasd asdasda dasdasda asdasda asdasda asdasd asdasd asdaf wgwrg wgwe gwegweg"
        )
    }
}