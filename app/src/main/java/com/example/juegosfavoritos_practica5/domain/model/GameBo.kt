package com.example.juegosfavoritos_practica5.domain.model

data class GameBo(
    val id: Int,
    val name: String,
    val studio: StudioBo,
    val launchDate: String,
    val cover: String,
    val platforms: List<String>,
    val pegi: String
)