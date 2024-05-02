package dev.unit6.healthypets.data.api

import dev.unit6.healthypets.data.api.model.FoodResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HealthyPetsService {

    @GET("v1/food/")
    suspend fun getAllFoods(): Response<List<FoodResponse>>

    @GET("v1/image/{name}")
    suspend fun getImage(
        @Path("name") name: String
    ): Response<ResponseBody>
}
