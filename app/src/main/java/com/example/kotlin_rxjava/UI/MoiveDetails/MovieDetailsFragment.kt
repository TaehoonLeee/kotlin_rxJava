package com.example.kotlin_rxjava.UI.MoiveDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kotlin_rxjava.R
import com.example.kotlin_rxjava.model.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val viewModel : MovieDetailsViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> { }
                Status.ERROR -> { }
                Status.LOADING -> { }
            }
        })
    }
}