package com.example.juegosfavoritos_practica5.data.domain.repository

import com.example.juegosfavoritos_practica5.R
import com.example.juegosfavoritos_practica5.ui.game.model.PlatformItem

object GameConstants {
    private const val EMPTY_STRING = ""
    const val PLATFORM_PLAYSTATION = "PlayStation"
    const val PLATFORM_XBOX = "Xbox"
    const val PLATFORM_NINTENDO = "Nintendo"
    private const val URL_PLAYSTATION =
        "https://assets.streamlinehq.com/image/private/w_300,h_300,ar_1/f_auto/v1/icons/video-games/playstation-isc3f6x60ta8lfk0hb24t.png/playstation-4ng359rzs0ryrajkol4q5.png?_a=DATAiZAAZAA0"
    private const val URL_XBOX =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9Rt_VfAs451lrAtGQHRC6tcMW5PoPB2h5uA&s"
    private const val URL_NINTENDO =
        "https://i.pinimg.com/236x/63/69/2d/63692dec066bac00bb10806880533901.jpg"
    private const val URL_ALL_PLATFORMS =
        "https://us.123rf.com/450wm/martialred/martialred1710/martialred171000010/88316249-geometric-shapes-vector-icon-for-education-apps-and-websites.jpg"
    private const val URL_PEGI_3 =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTq0VFJ5SrtF_e0oMczXOTo6P7mZx2sT2CC9w&s"
    private const val URL_PEGI_7 = "https://www.devuego.es/iconos/pegi_7f.jpg"
    private const val URL_PEGI_12 = "https://www.devuego.es/iconos/pegi_12f.jpg"
    private const val URL_PEGI_16 =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRv9eyVIIBn0731A7tOYH-R2pLOy7HCkBtjmQ&s"
    private const val URL_PEGI_18 = "https://media.s-bol.com/nlZ5LQ7lBZKY/oKpMAA/867x1200.jpg"
    fun getPlatformIcon(platformName: String): String {
        return when (platformName) {
            PLATFORM_PLAYSTATION -> URL_PLAYSTATION
            PLATFORM_XBOX -> URL_XBOX
            PLATFORM_NINTENDO -> URL_NINTENDO
            else -> EMPTY_STRING
        }
    }

    fun getPegiIcon(pegiAge: Int?): String {
        return when (pegiAge) {
            3 -> URL_PEGI_3
            7 -> URL_PEGI_7
            12 -> URL_PEGI_12
            16 -> URL_PEGI_16
            18 -> URL_PEGI_18
            else -> EMPTY_STRING
        }
    }

    fun getPlatformFilterList(): List<PlatformItem> {
        return listOf(
            PlatformItem(R.string.platform_playstation, URL_PLAYSTATION, PLATFORM_PLAYSTATION),
            PlatformItem(R.string.platform_xbox, URL_XBOX, PLATFORM_XBOX),
            PlatformItem(R.string.platform_nintendo, URL_NINTENDO, PLATFORM_NINTENDO),
            PlatformItem(R.string.platform_all, URL_ALL_PLATFORMS, null)
        )
    }

    fun getPegiOptions(): List<Pair<Int, String>> {
        return listOf(
            3 to URL_PEGI_3,
            7 to URL_PEGI_7,
            12 to URL_PEGI_12,
            16 to URL_PEGI_16,
            18 to URL_PEGI_18
        )
    }
}