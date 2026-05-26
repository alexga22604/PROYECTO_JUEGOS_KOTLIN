package com.example.juegosfavoritos_practica5.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studios")
data class StudioDbo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)