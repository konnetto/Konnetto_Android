package com.konnettoco.konnetto.data.model

data class Anime(
    var id: Int,
    var title: String,
    var rating: Double,
    var studio: String,
    var genre: List<String>,
    var voiceActors: List<String>,
    var synopsis: String,
    var status: String,
    var image: String,
    var currentEpisode: Int,
    var totalEpisode: Int,
    var aired: String,
    var duration: Int
)
