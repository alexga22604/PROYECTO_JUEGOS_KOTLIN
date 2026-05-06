package com.example.juegosfavoritos_practica5.data.mapper

import com.example.juegosfavoritos_practica5.data.entity.GameDbo
import com.example.juegosfavoritos_practica5.domain.model.GameBo
import com.example.juegosfavoritos_practica5.domain.model.StudioBo

fun GameDbo.toBo(studio: StudioBo) = GameBo(
    id = id,
    name = name,
    studio = studio,
    launchDate = launchDate,
    cover = cover,
    platforms = platforms.split(","),
    pegi = pegi
)

fun GameBo.toDbo() = GameDbo(
    id = id,
    name = name,
    studioId = studio.id,
    launchDate = launchDate,
    cover = cover,
    platforms = platforms.joinToString(","),
    pegi = pegi
)