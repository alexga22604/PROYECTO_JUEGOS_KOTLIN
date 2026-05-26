package com.example.juegosfavoritos_practica5.data.mapper

import com.example.juegosfavoritos_practica5.data.domain.model.GameBo
import com.example.juegosfavoritos_practica5.data.domain.model.StudioBo
import com.example.juegosfavoritos_practica5.data.entity.GameDbo

fun GameDbo.toBo(studioBo: StudioBo) = GameBo(
    id = id,
    name = name,
    studio = studioBo,
    launchDate = launchDate,
    cover = cover,
    platforms = platforms.split(",").filter { it.isNotBlank() },
    pegi = pegi
)

fun GameBo.toDbo() = GameDbo(
    id = id,
    name = name,
    studio = studio.name,
    launchDate = launchDate,
    cover = cover,
    platforms = platforms.joinToString(","),
    pegi = pegi
)