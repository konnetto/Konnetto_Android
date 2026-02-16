package com.konnettoco.konnetto.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Vertices
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.konnettoco.konnetto.ui.screen.settings.components.SeparatorTitle
import com.konnettoco.konnetto.ui.theme.KonnettoTheme


@Composable
fun EventCard(
    eventBackgroundImage: String? = null,
    locationEvent: String,
    categoryEvent: String,
    nameEvent: String,
    dateEvent: String,
    timeEvent: String,
    totalInterests: Int,
    onClick: () -> Unit
) {

    val backgroundImage = rememberAsyncImagePainter(model = eventBackgroundImage)
    var categoryEventText by remember { mutableStateOf(categoryEvent) }
    var locationEventText by remember { mutableStateOf(locationEvent) }
    var totalInterestsInt by remember { mutableStateOf(totalInterests) }
    var nameEventText by remember { mutableStateOf(nameEvent) }
    var dateEventText by remember { mutableStateOf(dateEvent) }
    var timeEventText by remember { mutableStateOf(timeEvent) }

    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column() {
            // 1. AREA GAMBAR (Pakai Box agar bisa ditumpuk)
            Box() {
                // Gambar Utama
                Image(
                    painter = backgroundImage,
                    modifier = Modifier.fillMaxWidth().height(160.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                // Badge "CONVENTION"
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(
                            color = Color.Black.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = categoryEventText,
                        color = Color.White,
                        fontSize = 12.sp,
                    )
                }
            }

            // 2. AREA KONTEN BAWAH
            Column(modifier = Modifier.padding(16.dp)) {

                // --- Trik Kotak Jadwal Hitam Mengambang ---
                Row (
                    modifier = Modifier
                        .wrapContentWidth()
                        .offset(y = (-33).dp)
                        .background(Color(0xFF1C2230), RoundedCornerShape(8.dp)),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.Person, contentDescription = "calendar")
                        }
                        Text(
                            text = dateEventText,
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        VerticalDivider(
                            modifier = Modifier.height(28.dp).padding(horizontal = 8.dp), // Add horizontal padding
                            thickness = 2.dp,
                            color = Color.LightGray
                        )

                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "calendar")
                        }
                        Text(
                            text = timeEventText,
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                        )

                    }
                }

                // Judul Event
                Text(nameEventText, fontSize = 24.sp, fontWeight = FontWeight.Bold)

                // Baris Lokasi
                Row() {
                    Icon(Icons.Default.LocationOn, contentDescription = null)
                    Text(locationEventText)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Baris Bawah (Avatar & Tombol)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween, // Kiri-Kanan otomatis menjauh
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Kiri: Avatar & Teks tertarik
                    Row {
                        /* Trik Avatar Menumpuk di sini */
                        Text("$totalInterestsInt tertarik")
                    }

                    // Kanan: Tombol Detail
                    Button(
                        onClick = onClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853))
                    ) {
                        Text(
                            "Lihat Detail",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PostCardItemPreview() {
    KonnettoTheme {
        EventCard(
            onClick = {},
            categoryEvent = "CONVETION",
            eventBackgroundImage = "https://ik.imagekit.io/6v306xm58/dimsum.jpg?updatedAt=1697428531462",
            locationEvent = "ICE BSD City, Tangerang",
            totalInterests = 3260,
            nameEvent = "Comifuro 19",
            dateEvent = "Sen, 12 Mei",
            timeEvent = "09:00 AM"
        )
    }
}