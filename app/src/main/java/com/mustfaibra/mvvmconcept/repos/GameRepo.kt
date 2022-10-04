package com.mustfaibra.mvvmconcept.repos

import com.mustfaibra.mvvmconcept.data.api.ApiServices
import com.mustfaibra.mvvmconcept.data.models.Game
import com.mustfaibra.mvvmconcept.sealed.DataResponse
import retrofit2.Response
import javax.inject.Inject

class GameRepo @Inject constructor(
    private val apiServices: ApiServices,
) {
    suspend fun getGames(): DataResponse<List<Game>?> {
        val response = apiServices.getGames()
        return if(response.isSuccessful){
            DataResponse.Success(data = response.body())
        } else {
            DataResponse.Error(message = response.message())
        }
    }

    suspend fun getGameDetails(id: Int) : DataResponse<Game>{
//        return apiServices.getGameDetails(id = id)
        TODO()
    }
}