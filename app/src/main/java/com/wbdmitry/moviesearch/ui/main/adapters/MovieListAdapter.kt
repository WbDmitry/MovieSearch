package com.wbdmitry.moviesearch.ui.main.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wbdmitry.moviesearch.databinding.FragmentItemMovieListBinding
import com.wbdmitry.moviesearch.model.entity.Movie
import com.wbdmitry.moviesearch.ui.main.movielist.MovieListFragment

class MovieListAdapter(private val onItemViewClickListener: MovieListFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<MovieListAdapter.MovieListHolder>() {

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
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = movieData.size

    inner class MovieListHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun bind(movie: Movie) = with(binding) {
            itemPosterMovieImageView.load("https://image.tmdb.org/t/p/original" + movie.poster_path)
            itemNameMovieTextView.text = movie.title
            itemCardView.setOnClickListener {
                onItemViewClickListener?.inItemViewClick(movie)
            }
        }
    }
}