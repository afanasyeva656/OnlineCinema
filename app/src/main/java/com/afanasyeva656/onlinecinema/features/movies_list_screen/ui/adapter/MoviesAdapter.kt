package com.afanasyeva656.onlinecinema.features.movies_list_screen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afanasyeva656.onlinecinema.R
import com.afanasyeva656.onlinecinema.databinding.ItemMovieBinding
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel
import com.bumptech.glide.Glide

class MoviesAdapter(
    private var movieModels: List<MovieDomainModel>,
    private val onItemClick: (movieModel: MovieDomainModel) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private lateinit var binding: ItemMovieBinding

    class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val textView: TextView

        init {
            this.textView = binding.tvScore
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.tvScore.text = movieModels[position].voteAverage.toString()
            Glide
                .with(binding.root)
                .load(movieModels[position].posterPath)
                .placeholder(R.drawable.ic_baseline_cloud_download_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.imageView)

            itemView.setOnClickListener { onItemClick(movieModels[position]) }
        }
    }

    override fun getItemCount(): Int {
        return movieModels.size
    }

    fun updateMovies(updatedMovieModels: List<MovieDomainModel>) {
        movieModels = updatedMovieModels
        notifyDataSetChanged()
    }
}