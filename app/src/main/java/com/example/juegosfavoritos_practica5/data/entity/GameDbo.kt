package com.example.juegosfavoritos_practica5.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameDbo(
    @PrimaryKey val id: Int,
    val name: String,
    val studioId: Int,
    val launchDate: String,
    val cover: String,
    val platforms: String,
    val pegi: String
)