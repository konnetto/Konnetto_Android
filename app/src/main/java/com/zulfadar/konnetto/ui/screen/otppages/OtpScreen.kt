package com.zulfadar.konnetto.ui.screen.otppages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zulfadar.konnetto.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun StickyTabLayoutScreen() {
    val tabs = listOf("Tab 1", "Tab 2", "Tab 3")
    var selectedTabIndex by remember { mutableStateOf(0) }

    val lazyListState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Sticky Tabs") })

        LazyColumn(state = lazyListState) {
            // Masukkan ProfileSection ke dalam LazyColumn agar bisa discroll
            item {
                ProfileSection(
                    displayname = "bambank",
                    username = "BamBank",
                    profilePict = R.drawable.logo.toString(),
                    friends = 500,
                    follows = 12,
                    biography = "Halo, ini adalah biodata user yang sangat panjang dan menjelaskan banyak hal menarik tentang kehidupannya sehari-hari."
                )
            }

            // Sticky Tab Row
            stickyHeader {
                ScrollableTabRow(
                    selectedTabIndex = selectedTabIndex,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    edgePadding = TabRowDefaults.ScrollableTabRowEdgeStartPadding,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = {
                                Text(
                                    fontSize = 19.sp,
                                    text = title
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            // Konten dari tab yang dipilih
            items(50) { index ->
                val contentText = when (selectedTabIndex) {
                    0 -> "Item $index dari Tab 1"
                    1 -> "Nothing"
                    2 -> "Item $index dari Tab 3"
                    else -> ""
                }
                Text(
                    text = contentText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun ProfileSection(
    displayname: String,
    username: String,
    profilePict: String?,
    friends: Int?,
    follows: Int?,
    biography: String?,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.height(120.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.header),
                contentDescription = "Cover Photo",
                modifier = Modifier
                    .sizeIn(maxHeight = 120.dp)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(profilePict?.toInt() ?: R.drawable.img),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(90.dp)
                    .offset(y = 60.dp, x = 20.dp)
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
                    .border(
                        width = 4.dp,
                        color = MaterialTheme.colorScheme.background,
                        shape = CircleShape
                    )
                    .padding(3.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.background)
                    .align(Alignment.BottomStart)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {

            Spacer(Modifier.widthIn(16.dp))
            Column {
                Row {
                    Column(
                        modifier = Modifier.padding(11.dp)
                    ) {
                        Text(
                            text = "Friends",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = friends.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Spacer(Modifier.widthIn(36.dp))
//                    Column(
//                        modifier = Modifier.padding(11.dp)
//                    ) {
//                        Text(
//                            text = "Follows",
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            color = MaterialTheme.colorScheme.onSurface
//                        )
//                        Text(
//                            text = follows.toString(),
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Normal,
//                            color = MaterialTheme.colorScheme.onSurface
//                        )
//                    }
                    Column(
                        modifier = Modifier.padding(11.dp)
                    ) {
                        Text(
                            text = "Posts",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = follows.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
        Text(
            text = displayname,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 18.dp)
        )
        Text(
            text = username,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 18.dp)
        )
        Spacer(Modifier.heightIn(12.dp))

        val displayedText = if (isExpanded || (biography?.length ?: 0) <= 50) biography else "${biography?.take(100) ?: ""}..."


        if (displayedText != null) {
            Text(
                text = displayedText,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
        if (biography?.length!! > 100) {
            Text(
                text = if (isExpanded) "See Less" else "See More",
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier
                    .clickable { isExpanded = !isExpanded }
                    .padding(vertical = 4.dp, horizontal = 18.dp)
                    .align(Alignment.Start)
            )
        }
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(12.dp)),
                onClick = {},
                enabled = true,
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    text = "Edit Profile",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.background
                )
            }
            Button(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(12.dp)),
                onClick = {},
                enabled = true,
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    text = "Social Media",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.background
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun StickyTablayout() {
    StickyTabLayoutScreen()
}
