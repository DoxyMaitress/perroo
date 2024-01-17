package com.example.perroo

import DogAdapter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perroo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val dogViewModel: DogViewModel by viewModels()
    private lateinit var dogAdapter: DogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar RecyclerView
        dogAdapter = DogAdapter { breed -> onBreedSelected(breed) }
        binding.recyclerViewDogs.adapter = dogAdapter
        binding.recyclerViewDogs.layoutManager = LinearLayoutManager(this)

        // Observar la lista de razas desde el ViewModel
        dogViewModel.dogBreeds.observe(this, Observer { breeds ->
            dogAdapter.setDogBreeds(breeds)
        })

        // Cargar las razas desde la API
        dogViewModel.fetchDogBreeds()
    }

    private fun onBreedSelected(breed: String) {
        // Aquí puedes manejar la selección de la raza, por ejemplo, cargar imágenes de esa raza
        dogViewModel.fetchDogImages(breed)
    }
}
