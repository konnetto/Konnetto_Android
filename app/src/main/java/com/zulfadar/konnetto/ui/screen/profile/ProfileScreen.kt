package com.zulfadar.konnetto.ui.screen.profile

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.zulfadar.konnetto.ui.theme.KonnettoTheme

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ProfileContent(
        username = "BamBank",
        profilePict = R.drawable.logo.toString(),
        friends = 500,
        follows = 12,
        biography = "Halo",
//        posts = null,
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@Composable
fun ProfileContent(
    username: String,
    profilePict: String?,
    friends: Int?,
    follows: Int?,
    biography: String?,
//    posts: List<Post>?,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ProfileTopAppBar(
                username = username,
                onBackClick = onBackClick
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            ProfileSection(
                username = username,
                profilePict = profilePict,
                friends = friends,
                follows = follows,
                biography = biography,
//            posts = posts,
                onBackClick = onBackClick,
            )
        }
    }
}

@Composable
fun ProfileSection(
    username: String,
    profilePict: String?,
    friends: Int?,
    follows: Int?,
    biography: String?,
//    posts: List<Post>?,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
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
            horizontalArrangement = Arrangement.End,
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
                    Column(
                        modifier = Modifier.padding(11.dp)
                    ) {
                        Text(
                            text = "Follows",
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
            text = username,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
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
        ProfileTabs()
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProfileTopAppBar(
    username: String,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
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
                text = username,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        },

    )
}

@Composable
fun ProfileTabs() {
    val tabs = listOf("Post", "My Picks")
    var selectedTab by remember { mutableIntStateOf(0) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            tabs.forEachIndexed { index, text ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedTab = index }
                ) {
                    Text(
                        text,
                        fontSize = 20.sp,
                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedTab == index) MaterialTheme.colorScheme.primary else Color.Gray,
                        modifier = Modifier.padding(8.dp)
                    )
                    // Garis bawah
                    Box(
                        modifier = Modifier
                            .height(3.dp)
                            .fillMaxWidth()
                            .background(
                                if (selectedTab == index) MaterialTheme.colorScheme.primary else Color.Transparent
                            )
                    )
                }
            }
        }
        // Garis bawah seluruh tab
        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    KonnettoTheme {
//        ProfileScreen()
        ProfileContent(
            username = "Bambank",
            profilePict = R.drawable.logo.toString(),
            friends = 500,
            follows = 50,
            biography = "AwikWok awok awok asaok asdao asdas asdas sdaddf fgrg rthr rthr wew erge rrth rthrth rthr rthrthr yj6uj6 6yj6yj6 6yj6yj 6yj6 lr ehr yjtyj  ege",
//            posts = null,
            onBackClick = {},
        )
    }
}