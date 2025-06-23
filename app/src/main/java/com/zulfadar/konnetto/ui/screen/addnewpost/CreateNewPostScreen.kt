package com.zulfadar.konnetto.ui.screen.addnewpost

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.ui.screen.addnewpost.components.AddCaptionTextField
import com.zulfadar.konnetto.ui.screen.addnewpost.components.AddMediaSection
import com.zulfadar.konnetto.ui.theme.KonnettoTheme

@Composable
fun CreateNewPostScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onPostButtonClick: () -> Unit
) {
    AddPostPageContent(
        modifier = modifier,
        onPostButtonClick = onPostButtonClick,
        onBackClick = onBackClick
    )
}

@Composable
fun AddPostPageContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onPostButtonClick: () -> Unit,
) {
    val caption = rememberSaveable { mutableStateOf("") }
    val tag = rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            AddNewPostTopAppBar(
                onBackClick = onBackClick,
                onPostButtonClick = onPostButtonClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            AddCaptionTextField(
                caption = caption.value
            )
            Spacer(modifier = Modifier.height(8.dp))
            AddMediaSection(
                onUploadButtonClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            AddTagSection(
                tag = tag.value
            )
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}

@Composable
fun AddTagSection(
    modifier: Modifier = Modifier,
    tag: String,
) {
    var tagEdt by remember { mutableStateOf(tag) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(vertical = 12.dp),
            text = "Tags",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        OutlinedTextField(
            value = tagEdt,
            onValueChange = { newTag ->
                tagEdt = newTag
            },
            modifier = modifier
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Add tags (e.g. #anime #cosplay)", color = Color.LightGray)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.LightGray,
                backgroundColor = MaterialTheme.colorScheme.surface,
            ),
            shape = RoundedCornerShape(12.dp),
            maxLines = 10,
            textStyle = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddNewPostTopAppBar(
    onBackClick: () -> Unit,
    onPostButtonClick: () -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_close_24),
                    contentDescription = "back button"
                )
            }
        },
        title = {
            Text(
                text = "Add New post",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            TextButton(
                modifier = Modifier
                    .clickable { onPostButtonClick }
                    .padding(horizontal = 12.dp),
                onClick = {}
            ) {
                Text(
                    text = "Post",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun CreateNewPostScreenPreview() {
    KonnettoTheme {
        AddPostPageContent(
            onPostButtonClick = {},
            onBackClick = {}
        )
    }
}