package com.example.juegosfavoritos_practica5.data.domain.repository

import com.example.juegosfavoritos_practica5.data.domain.model.GameBo
import com.example.juegosfavoritos_practica5.data.local.dao.GameDao
import com.example.juegosfavoritos_practica5.data.mapper.toBo
import com.example.juegosfavoritos_practica5.data.mapper.toDbo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(private val gameDao: GameDao) {

    fun getAllGames(): Flow<List<GameBo>> {
        return gameDao.getAllGames().map { dtoList ->
            dtoList.map { it.toBo() }
        }
    }

    fun getGamesByPlatform(platform: String): Flow<List<GameBo>> {
        return gameDao.getGamesByPlatform(platform).map { dtoList ->
            dtoList.map { it.toBo() }
        }
    }

    fun getGamesByStudio(studioName: String): Flow<List<GameBo>> {
        return gameDao.getGamesByStudio(studioName).map { dtoList ->
            dtoList.map { it.toBo() }
        }
    }

    suspend fun insertGame(game: GameBo) {
        gameDao.insertGame(game.toDbo())
    }

    suspend fun deleteGame(game: GameBo) {
        gameDao.deleteGame(game.toDbo())
    }
}