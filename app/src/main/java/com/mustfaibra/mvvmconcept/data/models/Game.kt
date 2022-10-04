package com.mustfaibra.mvvmconcept.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String?,
    val thumbnail: String,
    val genre: String,
)
