package com.example.juegosfavoritos_practica5.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameDbo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val studio: String,
    val launchDate: String,
    val cover: String,
    val platforms: String,
    val pegi: Int
)