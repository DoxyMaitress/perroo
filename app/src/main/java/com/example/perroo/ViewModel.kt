package com.example.perroo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogViewModel : ViewModel() {
    private val apiKey = "live_9jHbOVxM0pj9OG93oRu9iSOCfdKevl1KIPBQVVfukdPwoLpQXgJssHDeQJG4Hy34"

    private val apiService = Retrofit.Builder()
        .baseUrl("https://www.thedogapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DogApi::class.java)

    private val _dogBreeds = MutableLiveData<List<String>>()
    val dogBreeds: LiveData<List<String>> get() = _dogBreeds

    private val _selectedBreedImages = MutableLiveData<List<String>>()
    val selectedBreedImages: LiveData<List<String>> get() = _selectedBreedImages

    fun fetchDogBreeds() {
        viewModelScope.launch {
            try {
                val response = apiService.getDogBreeds(apiKey)
                handleBreedsResponse(response)
            } catch (e: Exception) {
                // Manejar el error
            }
        }
    }

    fun fetchDogImages(breed: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getDogImages(apiKey, breed)
                handleImagesResponse(response)
            } catch (e: Exception) {
                // Manejar el error
            }
        }
    }

    private fun handleBreedsResponse(response: Response<Map<String, List<String>>>) {
        if (response.isSuccessful) {
            val breeds = response.body()?.keys?.toList() ?: emptyList()
            _dogBreeds.postValue(breeds)
        }
    }

    private fun handleImagesResponse(response: Response<List<String>>) {
        if (response.isSuccessful) {
            val images = response.body() ?: emptyList()
            _selectedBreedImages.postValue(images.take(5))
        }
    }
}
