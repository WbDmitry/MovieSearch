package com.wbdmitry.moviesearch.ui.main.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wbdmitry.moviesearch.databinding.FragmentItemMovieListBinding
import com.wbdmitry.moviesearch.model.entity.Movie
import com.wbdmitry.moviesearch.ui.main.movielist.MovieListFragment

class MovieListCategory1Adapter(private val onItemViewClickListener: MovieListFragment.OnItemViewClickListener) :
    RecyclerView.Adapter<MovieListCategory1Adapter.MovieListHolder>() {

    private lateinit var binding: FragmentItemMovieListBinding
    private var movieData: List<Movie> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(data: List<Movie>) {
        movieData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder {
        binding = FragmentItemMovieListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieListHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    inner class MovieListHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun bind(movie: Movie) = with(binding) {
            binding.itemPosterMovieImageView.setImageResource(movie.poster)
            binding.itemNameMovieTextView.text = movie.title
            itemCardView.setOnClickListener {
                onItemViewClickListener.inItemViewClick(movie)
            }
        }
    }
}