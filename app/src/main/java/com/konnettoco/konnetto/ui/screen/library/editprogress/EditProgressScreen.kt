package com.konnettoco.konnetto.ui.screen.library.editprogress

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.data.local.model.MyLibraryItem
import com.konnettoco.konnetto.di.Injection
import com.konnettoco.konnetto.ui.common.UiState
import com.konnettoco.konnetto.ui.screen.library.editprogress.component.RoundedLinearProgress
import com.konnettoco.konnetto.ui.viewModelFactory.EditProgressViewModelFactory

@Composable
fun EditProgressScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    libraryItemId: Long,
    editProgressViewModel: EditProgressViewModel = viewModel(
        factory = EditProgressViewModelFactory(
            Injection.provideLibraryRepository(),
            libraryItemId = libraryItemId
        )
    ),
) {
    val uiState by editProgressViewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            EditProgressTopBar(
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            EditProgressBottomBar(
                onCancelClick = {},
                onSaveClick = {}
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (uiState) {
                is UiState.Loading -> {
                    Box(
                        modifier = modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Success -> {
                    val editProgress = (uiState as UiState.Success<MyLibraryItem>).data
                    EditProgressContent(
                        posterImage = editProgress.image,
                        title = editProgress.title,
                        currentEpisode = editProgress.currentEpisode,
                        totalEpisode = editProgress.totalEpisode,
                        year = editProgress.year,
                        notes = ""
                    )
                }

                is UiState.Error -> {

                }
            }
        }
    }
}

@Composable
fun EditProgressContent(
    modifier: Modifier = Modifier,
    posterImage: String,
    title: String,
    currentEpisode: Int,
    totalEpisode: Int,
    year: Int,
    notes: String
) {
    val painter = rememberAsyncImagePainter(model = posterImage)
    val painterState = painter.state

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
                )
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .background(
                                    color = Color.LightGray
                                )
                                .size(height = 90.dp, width = 70.dp)
                        )
                        if (painterState is AsyncImagePainter.State.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = Color.Gray
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .width(80.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$totalEpisode Episodes",
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                            Text(
                                text = " • ",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = year.toString(),
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }
                }
            }
            StatusSection(
                currentEpisode = currentEpisode,
                totalEpisode = totalEpisode
            )
            EpisodeProgressSection(
                currentEpisode = currentEpisode,
                totalEpisode = totalEpisode
            )
            YourRatingSection(
                maxRating = 5,
                initialRating = 0,
                onRatingChanged = {}
            )
            NotesSection(
                notes = notes
            )
        }
    }
}

@Composable
fun StatusSection(
    modifier: Modifier = Modifier,
    currentEpisode: Int,
    totalEpisode: Int,
) {
    val status = listOf("Watching", "Completed", "Plan to Watch")

    val defaultStatus = when {
        currentEpisode > 0 && currentEpisode != totalEpisode -> status[0] // Watching
        currentEpisode == totalEpisode -> status[1] // Completed
        else -> status[2] // Plan to Watch
    }

    val selectedStatus = remember { mutableStateOf(defaultStatus) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Status",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
            status.forEach { option ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = selectedStatus.value == option,
                        onClick = { selectedStatus.value = option }
                    )
                    Text(
                        text = option,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun EpisodeProgressSection(
    modifier: Modifier = Modifier,
    currentEpisode: Int,
    totalEpisode: Int,
) {
    var editCurrentEpisode by remember { mutableStateOf(currentEpisode) }

    val progressPercent = if (totalEpisode > 0) {
        (editCurrentEpisode * 100) / totalEpisode
    } else {
        0
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Episode Progress",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Episode Watched",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.LightGray
            )
            Spacer(Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // - button
                Surface(
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    color = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            if (editCurrentEpisode > 0) editCurrentEpisode--
                        }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "—",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }

                // Input episode
                OutlinedTextField(
                    modifier = modifier
                        .padding(horizontal = 12.dp)
                        .weight(1f),
                    value = editCurrentEpisode.toString(),
                    onValueChange = { newValue ->
                        editCurrentEpisode = newValue.toIntOrNull() ?: 0
                    },
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center,       // text center
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(12.dp),
                )

                // + button
                Surface(
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    color = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            if (editCurrentEpisode < totalEpisode) editCurrentEpisode++
                        }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "+",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
            Spacer(Modifier.height(2.dp))
            Text(
                text = "of $totalEpisode Episodes",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.LightGray
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Progress",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "$progressPercent%",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Spacer(Modifier.height(2.dp))
            RoundedLinearProgress(
                progress = if (totalEpisode > 0) {
                    editCurrentEpisode.toFloat() / totalEpisode.toFloat()
                } else {
                    0f
                },
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .height(12.dp)
                    .width(400.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = Color.LightGray,
            )
        }
    }
}

@Composable
fun YourRatingSection(
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
    initialRating: Int = 0,
    onRatingChanged: (Int) -> Unit = {}
) {
    var rating by remember { mutableStateOf(initialRating) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Your Rating",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 1..maxRating) {
                    val isSelected = i <= rating
                    Icon(
                        imageVector = if (isSelected) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = "Rating Star",
                        tint = if (isSelected) Color(0xFFFFD700) else Color.LightGray,
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable {
                                rating = i
                                onRatingChanged(rating)
                            }
                            .size(30.dp)
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$rating out of $maxRating Stars",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesSection(
    modifier: Modifier = Modifier,
    notes: String,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Notes",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(12.dp))
            var notesEdt by remember { mutableStateOf(notes) }
            var maxChar = 150

            Column(
                modifier = modifier.padding(vertical = 12.dp)
            ) {
                OutlinedTextField(
                    value = notesEdt,
                    onValueChange = { newNotes ->
                        if (newNotes.length <= maxChar) {
                            notesEdt = newNotes
                        }
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .height(90.dp), // minimal tinggi
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
//                        textColor = MaterialTheme.colorScheme.onSurface,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
//                        backgroundColor = MaterialTheme.colorScheme.surfaceContainerLowest,
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
                        text = "${notesEdt.length}/$maxChar",
                        modifier = Modifier
                            .padding(end = 8.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = if (notesEdt.length >= maxChar) Color.Red else Color.Gray
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProgressTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
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
            Text(
                text = "Edit Progress",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
        },
    )
}

@Composable
fun EditProgressBottomBar(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    BottomAppBar(
        modifier = modifier
            .height(160.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        contentColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 2.dp,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { onSaveClick() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(
                        text = "Save",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Button(
                    onClick = { onCancelClick() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFFCAFCCC)
                    ),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//private fun EditProgressPreview() {
//    KonnettoTheme {
//        EditProgressScreen(
//            onBackClick = {}
//        )
//    }
//}