import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.perroo.databinding.ListItemDogBinding

class DogAdapter(private val onBreedSelected: (String) -> Unit) :
    RecyclerView.Adapter<DogAdapter.DogViewHolder>() {

    private var dogBreeds: List<String> = emptyList()

    fun setDogBreeds(breeds: List<String>) {
        dogBreeds = breeds
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemDogBinding.inflate(inflater, parent, false)
        return DogViewHolder(binding, onBreedSelected)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val breed = dogBreeds[position]
        holder.bind(breed)
    }

    override fun getItemCount(): Int {
        return dogBreeds.size
    }

    inner class DogViewHolder(
        private val binding: ListItemDogBinding,
        private val onBreedSelected: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(breed: String) {
            binding.textViewBreed.text = breed
            binding.root.setOnClickListener {
                onBreedSelected.invoke(breed)
            }
        }
    }
}
