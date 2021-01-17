package com.example.kotlin_rxjava.UI.landing

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper

import com.example.kotlin_rxjava.R
import com.example.kotlin_rxjava.model.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.landing_fragment.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loading.*

@AndroidEntryPoint
class LandingFragment : Fragment(R.layout.landing_fragment) {

    private lateinit var adapter: MovieAdapter
    private val landingViewModel by viewModels<LandingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter()
        rvMovie.layoutManager = LinearLayoutManager(requireContext())
        rvMovie.adapter = adapter.withLoadStateFooter(
            MovieFooterStateAdapter {
                adapter.retry()
            }
        )

        adapter.addLoadStateListener { loadStates ->
            srl.isRefreshing = loadStates.source.refresh is LoadState.Loading
            llErrorContainer.isVisible = loadStates.source.refresh is LoadState.Error

            rvMovie.isVisible = !llErrorContainer.isVisible

            if (loadStates.source.refresh is LoadState.Error) {
                btnRetry.setOnClickListener {
                    adapter.retry()
                }

                llErrorContainer.isVisible = loadStates.source.refresh is LoadState.Error
                val errorMessage = (loadStates.source.refresh as LoadState.Error).error.message
                tvErrorMessage.text = errorMessage
            }
        }

        srl.setOnRefreshListener {
            landingViewModel.onRefresh()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        landingViewModel.Movies.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })
    }

}
