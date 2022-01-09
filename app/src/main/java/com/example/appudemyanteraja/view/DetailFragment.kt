package com.example.appudemyanteraja.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.appudemyanteraja.R
import com.example.appudemyanteraja.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var dogUuid = 0
    private var dogImage: ImageView? = null
    private var dogName: TextView? = null
    private var dogPurpose: TextView? = null
    private var dogTemperament: TextView? = null
    private var dogLifeSpan: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    /**
     * Initialization Id Layout and Inserting Function.
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dogImage = view.findViewById(R.id.iv_dog)
        dogName = view.findViewById(R.id.tv_dog_name)
        dogPurpose = view.findViewById(R.id.tv_dog_purpose)
        dogTemperament = view.findViewById(R.id.tv_dog_temperament)
        dogLifeSpan = view.findViewById(R.id.tv_dog_lifespan)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }
        viewModel.fetch()
        observeViewModel()
    }


    /**
     * Inserting Data ...
     */

    private fun observeViewModel() {
        viewModel.dogLiveData.observe(viewLifecycleOwner, Observer { dog ->
            dog?.let {
                dogName?.text = dog.dogBreed
                dogPurpose?.text = dog.bredFor
                dogTemperament?.text = dog.temperament
                dogLifeSpan?.text = dog.lifeSpan
            }
        })
    }
}