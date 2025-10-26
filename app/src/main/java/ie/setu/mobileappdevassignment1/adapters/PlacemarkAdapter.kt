package ie.setu.mobileappdevassignment1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.setu.mobileappdevassignment1.activities.PlacemarkListener
import ie.setu.mobileappdevassignment1.databinding.CardPlacemarkBinding
import ie.setu.mobileappdevassignment1.models.PlacemarkJSONStore
import ie.setu.mobileappdevassignment1.models.PlacemarkModel

class PlacemarkAdapter constructor(private var placemarks: List<PlacemarkModel>, private val listener: PlacemarkListener) :
    RecyclerView.Adapter<PlacemarkAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardPlacemarkBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }


    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val placemark = placemarks[holder.adapterPosition]
        holder.bind(placemark, listener)
    }

    override fun getItemCount(): Int = placemarks.size

    class MainHolder(private val binding : CardPlacemarkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(placemark: PlacemarkModel, listener: PlacemarkListener) {
            binding.placemarkTitle.text = placemark.title
            binding.description.text = placemark.description
            binding.root.setOnClickListener { listener.onPlacemarkClick(placemark) }
            binding.btnDelete.setOnClickListener { listener.onPlacemarkDeleteClick(placemark) }
        }
    }
}