package com.example.perroo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface DogApi {
    @GET("breeds/list/all")
    suspend fun getDogBreeds(@Header("x-api-key") apiKey: String): Response<Map<String, List<String>>>

    @GET("images/search")
    suspend fun getDogImages(
        @Query("breed") breed: String,
        @Query("limit") limit: Int = 5,
        @Header("x-api-key") apiKey: String
    ): Response<List<Map<String, Any>>>
}
