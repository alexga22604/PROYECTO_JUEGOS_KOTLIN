package com.example.juegosfavoritos_practica5.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studios")
data class StudioDbo(
    @PrimaryKey val id: Int,
    val name: String
)