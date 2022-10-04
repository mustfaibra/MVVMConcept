package com.mustfaibra.mvvmconcept.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustfaibra.mvvmconcept.data.models.Game

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGame(game: Game)

    @Query("SELECT * FROM game")
    fun getGames() : List<Game>
}