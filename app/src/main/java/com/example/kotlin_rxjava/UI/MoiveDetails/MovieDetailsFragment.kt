package com.example.kotlin_rxjava.UI.MoiveDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.kotlin_rxjava.R
import com.example.kotlin_rxjava.glide.GlideApp
import com.example.kotlin_rxjava.model.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_loading.*
import kotlinx.android.synthetic.main.movie_details_fragment.*

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val viewModel : MovieDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ibBack.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    showLoading(false)

                    val movie = it.data

                    GlideApp.with(ivBackDrop)
                        .load("https://image.tmdb.org/t/p/original${movie?.backdropPath}")
                        .placeholder(R.drawable.ic_image_photo)
                        .error(R.drawable.ic_film_error)
                        .into(ivBackDrop)

                    GlideApp.with(ivPoster)
                        .load("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
                        .placeholder(R.drawable.ic_image_photo)
                        .error(R.drawable.ic_film_error)
                        .into(ivPoster)

                    tvTitle.text = movie?.title

                    if( movie?.genres != null && movie.genres.isNotEmpty() ) {
                        val genres = movie.genres.joinToString(
                            separator = " | ",
                            transform = {genre -> genre.name}
                        )
                        tvGenres.text = genres
                    }
                    else tvGenres.visibility = View.GONE

                    if ( movie?.runtime != null ) tvRuntime.text = getString(R.string.format_runtime, movie.runtime)
                    else tvRuntime.visibility = View.GONE

                    if  ( movie?.releaseDate != null && movie.releaseDate.isNotBlank() ) tvReleaseDate.text = movie.releaseDate
                    else tvReleaseDate.visibility = View.GONE

                    movie?.voteAverage.let {
                        rating -> rbRatingBar.rating = (rating!! / 2).toFloat()
                    }

                    tvVoteCount.text = movie?.voteCount.toString()
                    tvOverView.text = movie?.overview


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