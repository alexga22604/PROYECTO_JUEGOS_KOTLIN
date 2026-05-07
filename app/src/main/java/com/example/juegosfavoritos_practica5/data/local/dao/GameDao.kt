package com.example.juegosfavoritos_practica5.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.juegosfavoritos_practica5.data.entity.GameDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GameDbo>>

    @Query("SELECT * FROM games WHERE platforms LIKE '%' || :platform || '%'")
    fun getGamesByPlatform(platform: String): Flow<List<GameDbo>>

    @Query("SELECT * FROM games WHERE studio = :studioName")
    fun getGamesByStudio(studioName: String): Flow<List<GameDbo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: GameDbo)

    @Delete
    suspend fun deleteGame(game: GameDbo)
}