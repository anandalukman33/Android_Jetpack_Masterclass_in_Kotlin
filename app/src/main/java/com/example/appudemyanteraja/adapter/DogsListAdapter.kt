package com.example.appudemyanteraja.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.appudemyanteraja.R
import com.example.appudemyanteraja.databinding.ItemDogBinding
import com.example.appudemyanteraja.model.DogBreed
import com.example.appudemyanteraja.util.getProgressDrawable
import com.example.appudemyanteraja.util.loadImage
import com.example.appudemyanteraja.view.ListFragmentDirections

class DogsListAdapter(
    private val dogsList: ArrayList<DogBreed>
) : RecyclerView.Adapter<DogsListAdapter.DogsViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateDogList(newDogsList: List<DogBreed>) {
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    /**
     * Declaration Variable for Material Needed
     */
//    private var dogName: TextView? = null
//    private var lifeSpan: TextView? = null
//    private var imageDog: ImageView? = null

    /**
     * Override Function Implementation
     */

    // Initialize Response size of DogBreed Class from Endpoint.
    override fun getItemCount() = dogsList.size

    // Initialization Layout For Implementation list of Item Dog.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.item_dog, parent, false)
        val dataBinding = DataBindingUtil.inflate<ItemDogBinding>(inflater, R.layout.item_dog, parent, false)
        return DogsViewHolder(dataBinding)
    }

    // Customization for Material Layout RecyclerView
    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {

        holder.view.dog = dogsList[position]


//        // Initialization id of item layout
//        dogName = holder.view.findViewById(R.id.tv_name)
//        lifeSpan = holder.view.findViewById(R.id.life_span)
//        imageDog = holder.view.findViewById(R.id.iv_item_dog)
//
//        // Inserting String and Url Image Api to TextView Layout
//        dogName?.text = dogsList[position].dogBreed
//        lifeSpan?.text = dogsList[position].lifeSpan
//        imageDog?.loadImage(dogsList[position].imageUrl, getProgressDrawable(imageDog?.context!!))
//
//        holder.view.setOnClickListener {
//            // Generate Uuid ketika klik gambar dog di halaman ListFragment
//            val action = ListFragmentDirections.actionListFragmentToDetailFragment()
//            action.dogUuid = dogsList[position].uuid
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    class DogsViewHolder(val view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)

}