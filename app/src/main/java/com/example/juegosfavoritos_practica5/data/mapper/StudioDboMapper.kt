package com.example.juegosfavoritos_practica5.data.mapper

import com.example.juegosfavoritos_practica5.data.domain.model.StudioBo
import com.example.juegosfavoritos_practica5.data.entity.StudioDbo

fun StudioDbo.toBo() = StudioBo(
    id = id,
    name = name
)

fun StudioBo.toDbo() = StudioDbo(
    id = id,
    name = name
)