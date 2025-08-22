package com.konnettoco.konnetto.data.model

data class MyLibraryItem(
    var id: Int,
    var title: String,
    var rating: Double,
    var studio: String,
    var genre: String ,
    var synopsis: String,
    var image: String,
    var currentEpisode: Int,
    var totalEpisode: Int,
)
