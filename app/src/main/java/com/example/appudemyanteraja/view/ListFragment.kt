package com.example.appudemyanteraja.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.appudemyanteraja.R
import com.example.appudemyanteraja.adapter.DogsListAdapter
import com.example.appudemyanteraja.viewmodel.ListViewModel

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private var dogsListAdapter = DogsListAdapter(arrayListOf())
    private var recyclerDogList: RecyclerView? = null
    private var listError: TextView? = null
    private var loading: ProgressBar? = null
    private var swipeRefresh: SwipeRefreshLayout? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    /**
     * Initialization Id Layout
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerDogList = view.findViewById(R.id.dogs_list)
        listError = view.findViewById(R.id.list_error)
        loading = view.findViewById(R.id.loading_view)
        swipeRefresh = view.findViewById(R.id.refresh_layout)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)


        recyclerDogList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter
        }


        swipeRefresh?.setOnRefreshListener { // Action for Swipe Refresh Layout
            recyclerDogList?.visibility = View.GONE
            listError?.visibility = View.GONE
            loading?.visibility = View.GONE
            viewModel.refreshBypassCache() // ketika di refresh akan memanggil atau memperbaharui data dari endpoint/api
            //viewModel.refresh()
            swipeRefresh?.isRefreshing = false
        }
        viewModel.refresh()
        observeViewModel()
    }

    /**
     * Pengaturan Menu
     */

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.actionSettings -> {
                view?.let { Navigation.findNavController(it).navigate(ListFragmentDirections.actionSetting()) }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    /**
     * Inserting Data ...
     */

    private fun observeViewModel() {
        viewModel.dogs.observe(viewLifecycleOwner, Observer { dogs ->
            dogs?.let {
                recyclerDogList?.visibility = View.VISIBLE
                dogsListAdapter.updateDogList(dogs)
            }
        })

        viewModel.dogsLoadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                listError?.visibility =
                    if (it) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                loading?.visibility =
                    if (it) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }

                if (it) {
                    listError?.visibility = View.GONE
                    recyclerDogList?.visibility = View.GONE
                }
            }
        })
    }
}