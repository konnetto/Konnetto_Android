package com.konnettoco.konnetto.ui.screen.profile.shareprofile.component

import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareProfileTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Share Profile",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back),
                    contentDescription = "back button",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun ShareProfileTopBarPrev() {
    KonnettoTheme {
        ShareProfileTopBar(
            onBackClick = {}
        )
    }
}