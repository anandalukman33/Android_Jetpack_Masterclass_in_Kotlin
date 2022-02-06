package com.example.appudemyanteraja.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.appudemyanteraja.R
import com.example.appudemyanteraja.databinding.FragmentDetailBinding
import com.example.appudemyanteraja.model.PalleteBean
import com.example.appudemyanteraja.util.getProgressDrawable
import com.example.appudemyanteraja.util.loadImage
import com.example.appudemyanteraja.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentDetailBinding
    private var sendSmsStarted = false
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
        setHasOptionsMenu(true)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
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


        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch(dogUuid)  //mengambil data dari DetailViewModel yang sudah dibawa olehnya dari database
        observeViewModel()
    }


    /**
     * Inserting Data ...
     */

    private fun observeViewModel() {
        viewModel.dogLiveData.observe(viewLifecycleOwner, Observer { dog ->
            dog?.let {
                dataBinding.dog = dog
//                dogName?.text = dog.dogBreed
//                dogPurpose?.text = dog.bredFor
//                dogTemperament?.text = dog.temperament
//                dogLifeSpan?.text = dog.lifeSpan
//
                context?.let { // Mengambil gambar dari database berdasarkan uuid yang dipilih
                    dogImage?.loadImage(dog.imageUrl, getProgressDrawable(it))
                }

                it.imageUrl.let {
                    setupBackgroundColour(it)
                }

            }
        })
    }

    private fun setupBackgroundColour(url: String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object: CustomTarget<Bitmap>(){

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate { palette ->
                            val intColor = palette?.vibrantSwatch?.rgb ?: 0
                            val myPalette = PalleteBean(intColor)
                            dataBinding.pallete = myPalette
                        }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_send_sms -> {
                sendSmsStarted = true
                (activity as MainActivity).checkSmsPermission()
            }

            R.id.action_share -> {

            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun onPermissionResult(permissionGranted: Boolean) {

    }
}