package com.example.kotlin_rxjava.UI.landing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_rxjava.R
import com.example.kotlin_rxjava.glide.GlideApp
import com.example.kotlin_rxjava.model.Movie

import kotlinx.android.synthetic.main.item_movie.view.*
import timber.log.Timber


class MovieAdapter : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { holder.bind(it) }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            itemView.setOnClickListener {
                val directions =
                    LandingFragmentDirections.actionLandingFragmentToMovieDetailsFragment(movie.id!!)
                it.findNavController().navigate(directions)
            }
            itemView.apply {
                GlideApp.with(ivPoster)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .placeholder(R.drawable.ic_image_photo)
                    .error(R.drawable.ic_film_error)
                    .into(ivPoster)
                tvTitle.text = movie.title
                tvOverView.text = movie.overview
                tvReleaseDate.text = movie.releaseDate
            }
        }
        companion object {
            fun from(parent: ViewGroup) : MovieViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemView = inflater.inflate(R.layout.item_movie, parent, false)

                return MovieViewHolder(itemView)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }
}