package com.example.appudemyanteraja.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appudemyanteraja.R
import com.example.appudemyanteraja.model.DogBreed

class DogsListAdapter(
    private val dogsList: ArrayList<DogBreed>
) : RecyclerView.Adapter<DogsListAdapter.DogsViewHolder>() {

    fun updateDogList(newDogsList: List<DogBreed>) {
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    /**
     * Declaration Variable for Material Needed
     */
    private var dogName: TextView? = null
    private var lifeSpan: TextView? = null

    /**
     * Override Function Implementation
     */

    // Initialize Response size of DogBreed Class from Endpoint.
    override fun getItemCount() = dogsList.size

    // Initialization Layout For Implementation list of Item Dog.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_dog, parent, false)
        return DogsViewHolder(view)
    }

    // Customization for Material Layout RecyclerView
    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {

        // Initialization id of item layout
        dogName = holder.view.findViewById(R.id.tv_dog_name)
        lifeSpan = holder.view.findViewById(R.id.tv_dog_lifespan)

        // Inserting String Api to TextView Layout
        dogName?.text = dogsList[position].dogBreed
        lifeSpan?.text = dogsList[position].lifeSpan

    }

    class DogsViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}