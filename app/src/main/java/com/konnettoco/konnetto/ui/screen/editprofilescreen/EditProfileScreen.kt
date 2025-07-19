package com.konnettoco.konnetto.ui.screen.editprofilescreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.R

@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    EditProfileContent(
        modifier = modifier,
        profilePict = R.drawable.logo.toString(),
        displayname = "Uzumaki Uchiha bambank",
        username = "bambank",
        bio = "awok awoako asdaomda faf a0jsda sdas0fasf asf0sd sdsdf-sd sdg",
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EditProfileContent(
    modifier: Modifier = Modifier,
    profilePict: String?,
    displayname: String,
    username: String,
    bio: String,
    onBackClick: () -> Unit
) {
    val verticalScrollState = rememberScrollState()
    var displaynameEdt by remember { mutableStateOf(displayname) }
    var usernameEdt by remember { mutableStateOf(username) }
    var bioEdt by remember { mutableStateOf(bio) }

    Scaffold(
        modifier = modifier,
        topBar = {
            EditProfileTopAppBar(
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(verticalScrollState),
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.padding(vertical = 12.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        painter = painterResource(profilePict?.toInt() ?: R.drawable.img),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(120.dp)
                            .aspectRatio(1f, matchHeightConstraintsFirst = true)
                            .border(
                                width = 4.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = CircleShape
                            )
                            .padding(3.dp)
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colorScheme.background)
                    )
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Profile Picture",
                        tint = Color.White,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                            .align(Alignment.BottomEnd)
                            .padding(2.dp)
                    )
                }
                Text(
                    text = "Change profile picture",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "Name",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    value = displaynameEdt,
                    onValueChange = {
                        displaynameEdt = it
                    },
                    placeholder = { Text(displaynameEdt, color = Color.LightGray) },
                    singleLine = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "displayname Icon",
                            tint = Color.LightGray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = Color.LightGray,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
            Spacer(Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "Username",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    value = usernameEdt,
                    onValueChange = {
                        usernameEdt = it
                            .take(24)
                            .filter { c -> c.isLowerCase() || c.isDigit() || c == '.' || c == '_' }
                            .lowercase()
                    },
                    placeholder = {
                        Text(
                            "@",
                            color = Color.LightGray
                        )
                    },
                    singleLine = true,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.at_24),
                            contentDescription = "displayname Icon",
                            tint = Color.LightGray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = Color.LightGray,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
            Spacer(Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "Bio",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(vertical = 8.dp),
                    value = bioEdt,
                    onValueChange = {
                        bioEdt = it
                    },
                    placeholder = {
                        Text(bioEdt)
                    },
                    maxLines = 6,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "displayname Icon",
                            tint = Color.LightGray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EditProfileTopAppBar(
    onBackClick: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back),
                    contentDescription = "back button"
                )
            }
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Edit Profile",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
        actions = {
            TextButton(
                onClick = {},
                colors = ButtonDefaults.textButtonColors(
                    Color.Transparent
                )
            ) {
                Text("Save",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@Preview
@Composable
private fun EditProfilePreview() {
    EditProfileContent(
        profilePict = R.drawable.logo.toString(),
        displayname = "Bambank",
        username = "uzumakibambank",
        bio = "wkwkwkwkwkwkwk",
        onBackClick = {}
    )
}