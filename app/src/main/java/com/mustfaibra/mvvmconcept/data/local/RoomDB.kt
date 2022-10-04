package com.mustfaibra.mvvmconcept.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mustfaibra.mvvmconcept.data.models.Game

@Database(
    entities = [
        Game::class
    ],
    version = 1,
    exportSchema = false,
)
abstract class RoomDB : RoomDatabase() {
    abstract fun getDao(): RoomDao
}