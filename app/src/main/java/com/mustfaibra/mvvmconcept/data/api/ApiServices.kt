package com.mustfaibra.mvvmconcept.data.api

import com.mustfaibra.mvvmconcept.data.models.Game
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("games")
    suspend fun getGames(): Response<List<Game>>

    @GET("game?id={gameId}")
    suspend fun getGameDetails(
        @Path("gameId") id: Int
    ): Response<Game>
}