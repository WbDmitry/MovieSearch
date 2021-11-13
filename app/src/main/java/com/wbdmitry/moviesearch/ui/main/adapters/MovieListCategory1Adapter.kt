package com.wbdmitry.moviesearch.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wbdmitry.moviesearch.R
import com.wbdmitry.moviesearch.databinding.FragmentItemMovieListBinding
import com.wbdmitry.moviesearch.model.Movie
import com.wbdmitry.moviesearch.ui.main.movielist.MovieListFragment

class MovieListCategory1Adapter(private val onItemViewClickListener: MovieListFragment.OnItemViewClickListener) :
    RecyclerView.Adapter<MovieListCategory1Adapter.MovieListHolder>() {

    private lateinit var binding: FragmentItemMovieListBinding

    var movieListCategory1 = listOf(
        Movie(R.drawable.ic_launcher_background, "Фильм 1", "Описание фильма 1"),
        Movie(R.drawable.ic_launcher_background, "Фильм 2", "Описание фильма 2"),
        Movie(R.drawable.ic_launcher_background, "Фильм 3", "Описание фильма 3"),
        Movie(R.drawable.ic_launcher_background, "Фильм 4", "Описание фильма 4"),
        Movie(R.drawable.ic_launcher_background, "Фильм 5", "Описание фильма 5"),
        Movie(R.drawable.ic_launcher_background, "Фильм 6", "Описание фильма 6"),
        Movie(R.drawable.ic_launcher_background, "Фильм 7", "Описание фильма 7"),
        Movie(R.drawable.ic_launcher_background, "Фильм 8", "Описание фильма 8")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder {
        binding = FragmentItemMovieListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieListHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
        holder.bind(movieListCategory1[position])
    }

    override fun getItemCount(): Int {
        return movieListCategory1.size
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