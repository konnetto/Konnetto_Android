package com.konnettoco.konnetto.ui.screen.library.mylibrarydetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.data.model.MyLibraryItem
import com.konnettoco.konnetto.di.Injection
import com.konnettoco.konnetto.ui.common.UiState
import com.konnettoco.konnetto.ui.screen.profile.userprofile.components.RoundedLinearProgressIndicator
import com.konnettoco.konnetto.ui.theme.KonnettoTheme
import com.konnettoco.konnetto.ui.viewModelFactory.LibraryItemDetailViewModelFactory
import com.konnettoco.konnetto.utils.getGenreColor

@Composable
fun LibraryDetailScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onEditButtonClick: (Long) -> Unit,
    libraryItemId: Long,
    libraryDetailViewModel: MyLibraryDetailViewModel = viewModel(
        factory = LibraryItemDetailViewModelFactory(
            Injection.provideLibraryRepository(),
            libraryItemId = libraryItemId
        )
    ),
) {
    val uiState by libraryDetailViewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            when (uiState) {
                is UiState.Success -> {
                    val libraryItem = (uiState as UiState.Success<MyLibraryItem>).data
                    LibraryDetailTopBar(
                        onBackClick = onBackClick,
                        title = libraryItem.title
                    )
                }
                else -> {
                    LibraryDetailTopBar(
                        onBackClick = onBackClick,
                        title = "Detail"
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
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
                    val libraryItem = (uiState as UiState.Success<MyLibraryItem>).data
                    LibraryDetailContent(
                        modifier = modifier,
                        id = libraryItem.id.toLong(),
                        posterImage = libraryItem.image,
                        title = libraryItem.title,
                        rating = libraryItem.rating,
                        status = libraryItem.status,
                        year = libraryItem.year,
                        genres = libraryItem.genre,
                        studio = libraryItem.studio,
                        synopsis = libraryItem.synopsis,
                        currentEpisode = libraryItem.currentEpisode,
                        totalEpisode = libraryItem.totalEpisode,
                        duration = libraryItem.duration,
                        onEditButtonClick = onEditButtonClick,
                    )
                }

                is UiState.Error -> {

                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LibraryDetailContent(
    modifier: Modifier = Modifier,
    onEditButtonClick: (Long) -> Unit,
    id: Long,
    posterImage: String,
    title: String,
    rating: Double,
    genres: List<String>,
    year: Int,
    studio: String,
    status: String,
    synopsis: String,
    currentEpisode: Int,
    totalEpisode: Int,
    duration: Int,
) {
    val painter = rememberAsyncImagePainter(model = posterImage)
    val painterState = painter.state

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
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
                        .size(height = 150.dp, width = 100.dp)
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
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.fillMaxWidth().width(80.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                    Text(
                        text = rating.toString(),
                        fontSize = 16.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = " • ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = year.toString(),
                        fontSize = 16.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                //Genres
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    genres.forEach { genre ->
                        val (bgColor, textColor) = getGenreColor(genre)
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = textColor, // atau gunakan warna lain
                                    shape = RoundedCornerShape(50)
                                )
                                .background(
                                    color = bgColor,
                                    shape = RoundedCornerShape(50)
                                )
                                .padding(vertical = 2.dp, horizontal = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = genre,
                                fontSize = 12.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.SemiBold,
                                color = textColor,
                            )
                        }
                        Spacer(Modifier.width(4.dp))
                    }
                }
            }
        }
        //progress section
        Card(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
            )
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Progress",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = "Episode $currentEpisode of $totalEpisode",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RoundedLinearProgressIndicator(
                        progress = if (totalEpisode > 0) {
                            currentEpisode.toFloat() / totalEpisode.toFloat()
                        } else {
                            0f
                        },
                        modifier = Modifier
                            .padding(vertical = 10.dp)
//                            .align(Alignment.CenterHorizontally)
                            .height(12.dp)
                            .width(270.dp),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = Color.LightGray,
                    )
                    IconButton(
                        onClick = { onEditButtonClick(id) },
                        modifier = Modifier.padding(horizontal = 4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(MaterialTheme.colorScheme.surfaceContainerLow),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Transparent
                        ),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
        //synopsis section
        Card(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
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
                    text = "Synopsis",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 12.dp),
                    text = synopsis,
                    textAlign = TextAlign.Justify,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
        // Information section
        Card(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
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
                    text = "Information",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Episodes",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Text(
                        text = totalEpisode.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Status",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Text(
                        text = status,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Studio",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Text(
                        text = studio,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Duration",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Text(
                        text = "$duration min",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 12.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFFD0D0D0)
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.share_icon_outlined),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = "Share with friends",
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 4.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFFFFB9C8)
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Red
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = "Remove from Library",
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Red,
                )
            }
        }
        Spacer(Modifier.height(120.dp))
    }
}

@Composable
fun RoundedLinearProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = Color.LightGray,
    cornerRadius: Dp = 50.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(trackColor),
        contentAlignment = Alignment.Center
    ) {
        LinearProgressIndicator(
            progress = progress,
            color = color,
            trackColor = Color.Transparent,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(cornerRadius))
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryDetailTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun LibraryDetailPrev() {
    KonnettoTheme {
        LibraryDetailContent(
            posterImage = "",
            title = "Attack on Titan",
            rating = 7.90,
            status = "Finished",
            year = 2013,
            genres = listOf("Fantasy", "Action", "Seinen"),
            studio = "Mappa",
            synopsis = "For over a century, humanity has lived behind three massive walls—Maria, Rose, and Sheena—to protect themselves from mysterious man-eating giants known as Titans. Their fragile peace shatters when a colossal Titan breaches the outer wall, unleashing chaos and destruction.",
            currentEpisode = 8,
            totalEpisode = 25,
            duration = 24,
            onEditButtonClick = {},
            id = 1
        )
    }
}