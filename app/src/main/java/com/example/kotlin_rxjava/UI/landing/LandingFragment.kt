package com.example.kotlin_rxjava.UI.landing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper

import com.example.kotlin_rxjava.R
import com.example.kotlin_rxjava.model.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.landing_fragment.*
import kotlinx.android.synthetic.main.layout_loading.*

@AndroidEntryPoint
class LandingFragment : Fragment(R.layout.landing_fragment) {

    private lateinit var adapter: MovieAdapter
    private val landingViewModel by viewModels<LandingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter()
        rvMovie.layoutManager = LinearLayoutManager(requireContext())
        rvMovie.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        landingViewModel.Movies.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    showLoading(false)
                    adapter.setMovies(it.data!!)
                }
                Status.ERROR -> {
                    showLoading(false)
                    Snackbar.make(requireView(), it.message!!, Snackbar.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    showLoading(true)
                }
            }
        })
    }

    private fun showLoading(isShow : Boolean) {
        if (isShow) {
            loadingContainer.visibility = View.VISIBLE
        }
        else loadingContainer.visibility = View.GONE
    }
}
