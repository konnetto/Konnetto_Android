package com.konnettoco.konnetto.ui.screen.profile.shareprofile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.screen.profile.shareprofile.component.ProfileSectionShare
import com.konnettoco.konnetto.ui.screen.profile.shareprofile.component.QuickShareSection
import com.konnettoco.konnetto.ui.screen.profile.shareprofile.component.ShareByQrSection
import com.konnettoco.konnetto.ui.screen.profile.shareprofile.component.ShareLinkSection
import com.konnettoco.konnetto.ui.screen.profile.shareprofile.component.ShareProfileCardSection
import com.konnettoco.konnetto.ui.screen.profile.shareprofile.component.ShareProfileTopBar
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun ShareProfileScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    ShareProfileContent(
        modifier = modifier,
        onBackClick = onBackClick,
        onCopyClick = {},
        profilePict = R.drawable.logo.toString(),
        username = "charaznable08",
        displayname = "Char Aznable",
        bio = "asda awa aomsdasma awokawokawok wkwkwkwk",
        friendsCount = 4,
        watchedCount = 12,
        profileLink = "https://www.konnnetto.com/charaznable08"
    )
}

@Composable
fun ShareProfileContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCopyClick: () -> Unit,
    profilePict: String?,
    username: String,
    displayname: String,
    bio: String,
    friendsCount:Int,
    watchedCount: Int,
    profileLink: String,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            ShareProfileTopBar(
                onBackClick = onBackClick
            )
        }
    ) { innerpadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()
        ) {
            item {
                Spacer(Modifier.height(12.dp))
                ProfileSectionShare(
                    profilePict = profilePict,
                    username = username,
                    displayname = displayname,
                    friends = friendsCount,
                    watched = watchedCount
                )
                Spacer(Modifier.height(24.dp))
                ShareLinkSection(
                    onCopyClick = onCopyClick,
                    profileLink = profileLink,
                )
                QuickShareSection(
                    onXShareClick = {},
                    onInstagramShareClick = {},
                    onWhatsAppShareClick = {},
                    onMoreClick = {},
                )
                ShareByQrSection(
                    onSaveQrCodeClick = {}
                )
                ShareProfileCardSection(
                    profilePict = profilePict,
                    displayname = displayname,
                    username = username,
                    watchedCount = watchedCount,
                    friendsCount = friendsCount,
                    bio = bio,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShareProfileContentPrev() {
    KonnettoTheme {
        ShareProfileContent(
            onBackClick = {},
            profilePict = R.drawable.logo.toString(),
            username = "charaznable08",
            displayname = "Char Aznable",
            bio = "Ini Bio",
            friendsCount = 4,
            watchedCount = 12,
            onCopyClick = {},
            profileLink = "https://www.konnnetto.com/charaznable08",
        )
    }
}