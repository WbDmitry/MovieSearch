package com.wbdmitry.moviesearch.ui.main.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wbdmitry.moviesearch.R
import com.wbdmitry.moviesearch.databinding.FragmentMovieListBinding
import com.wbdmitry.moviesearch.model.Movie
import com.wbdmitry.moviesearch.ui.main.adapters.MovieListCategory1Adapter
import com.wbdmitry.moviesearch.ui.main.adapters.MovieListCategory2Adapter
import com.wbdmitry.moviesearch.ui.main.movieInfo.MovieInfoFragment

class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListBinding

    private val onListItemClickListener = object : OnItemViewClickListener {
        override fun inItemViewClick(movie: Movie) {
            activity?.supportFragmentManager?.let {
                val bundle = Bundle()
                bundle.putParcelable(MovieInfoFragment.BUNDLE_EXTRA, movie)
                it.beginTransaction()
                    .add(R.id.main_container, MovieInfoFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    }

    private val adapterCategory1 = MovieListCategory1Adapter(onListItemClickListener)
    private val adapterCategory2 = MovieListCategory2Adapter(onListItemClickListener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.category1RecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.category1RecyclerView.adapter = adapterCategory1
        adapterCategory1.movieListCategory1

        binding.category2RecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.category2RecyclerView.adapter = adapterCategory2
        adapterCategory2.movieListCategory2
    }

    interface OnItemViewClickListener {
        fun inItemViewClick(movie: Movie)
    }

    companion object {
        fun newInstance() =
            MovieListFragment()
    }
}