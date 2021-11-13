package com.wbdmitry.moviesearch.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wbdmitry.moviesearch.R
import com.wbdmitry.moviesearch.databinding.FragmentItemMovieListBinding
import com.wbdmitry.moviesearch.model.Movie
import com.wbdmitry.moviesearch.ui.main.movielist.MovieListFragment

class MovieListCategory2Adapter(private val onItemViewClickListener: MovieListFragment.OnItemViewClickListener) :
    RecyclerView.Adapter<MovieListCategory2Adapter.MovieListHolder>() {

    private lateinit var binding: FragmentItemMovieListBinding

    val movieListCategory2 = listOf(
        Movie(R.drawable.ic_launcher_foreground, "1 Фильм", "1 Описание фильма"),
        Movie(R.drawable.ic_launcher_foreground, "2 Фильм", "2 Описание фильма"),
        Movie(R.drawable.ic_launcher_foreground, "3 Фильм", "3 Описание фильма"),
        Movie(R.drawable.ic_launcher_foreground, "4 Фильм", "4 Описание фильма"),
        Movie(R.drawable.ic_launcher_foreground, "5 Фильм", "5 Описание фильма"),
        Movie(R.drawable.ic_launcher_foreground, "6 Фильм", "6 Описание фильма"),
        Movie(R.drawable.ic_launcher_foreground, "7 Фильм", "7 Описание фильма"),
        Movie(R.drawable.ic_launcher_foreground, "8 Фильм", "8 Описание фильма")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder {
        binding = FragmentItemMovieListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieListHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
        holder.bind(movieListCategory2[position])
    }

    override fun getItemCount(): Int {
        return movieListCategory2.size
    }

    inner class MovieListHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun bind(movie: Movie) = with(binding) {
            itemPosterMovieImageView.setImageResource(movie.poster)
            itemNameMovieTextView.text = movie.title
            itemCardView.setOnClickListener {
                onItemViewClickListener.inItemViewClick(movie)
            }
        }
    }
}