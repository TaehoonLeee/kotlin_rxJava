package com.example.kotlin_rxjava.UI.landing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kotlin_rxjava.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.landing_fragment.*
import timber.log.Timber

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
            adapter.setMovies(it)
        })
    }
}
