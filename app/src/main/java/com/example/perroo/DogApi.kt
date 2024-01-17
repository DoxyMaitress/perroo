package com.example.perroo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

// DogApi.kt
interface DogApi {
    @GET("breeds/list/all")
    suspend fun getDogBreeds(@Header("x-api-key") apiKey: String): Response<Map<String, List<String>>>

    @GET("breed/{breed}/images")
    suspend fun getDogImages(@Header("x-api-key") apiKey: String, @Path("breed") breed: String): Response<List<String>>
}

