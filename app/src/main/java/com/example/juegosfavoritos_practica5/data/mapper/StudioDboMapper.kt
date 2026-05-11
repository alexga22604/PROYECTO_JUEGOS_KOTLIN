package com.example.juegosfavoritos_practica5.data.mapper

import com.example.juegosfavoritos_practica5.data.entity.StudioDbo
import com.example.juegosfavoritos_practica5.domain.model.StudioBo

fun StudioDbo.toBo() = StudioBo(
    id = id,
    name = name
)

fun StudioBo.toDbo() = StudioDbo(
    id = id,
    name = name
)