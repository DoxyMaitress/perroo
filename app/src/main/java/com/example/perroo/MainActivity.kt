package com.example.perroo
import com.example.perroo.DogAdapter
import android.os.Bundle
import android.util.Log
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

        // Configurar RecyclerView
        dogAdapter = DogAdapter { breed -> onBreedSelected(breed) }
        binding.recyclerViewDogs.adapter = dogAdapter
        binding.recyclerViewDogs.layoutManager = LinearLayoutManager(this)

        dogViewModel.dogBreeds.observe(this, Observer { breeds ->
            dogAdapter.setDogBreeds(breeds)
            Log.d("MainActivity", "Lista de razas cargada correctamente: $breeds")
        })
        // Cargar las razas desde la API
        dogViewModel.fetchDogBreeds()
    }

    private fun onBreedSelected(breed: String) {
        // Aquí puedes manejar la selección de la raza, por ejemplo, cargar imágenes de esa raza
        dogViewModel.fetchDogImages(breed)
    }
}
