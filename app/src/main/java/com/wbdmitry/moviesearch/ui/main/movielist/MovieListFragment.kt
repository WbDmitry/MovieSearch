package com.wbdmitry.moviesearch.ui.main.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wbdmitry.moviesearch.R
import com.wbdmitry.moviesearch.databinding.FragmentMovieListBinding
import com.wbdmitry.moviesearch.model.AppState
import com.wbdmitry.moviesearch.model.entity.Movie
import com.wbdmitry.moviesearch.ui.main.adapters.MovieListCategory1Adapter
import com.wbdmitry.moviesearch.ui.main.adapters.MovieListCategory2Adapter
import com.wbdmitry.moviesearch.ui.main.movieInfo.MovieInfoFragment

class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: MovieListViewModel


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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.category1RecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.category1RecyclerView.adapter = adapterCategory1

        binding.category2RecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.category2RecyclerView.adapter = adapterCategory2

        viewModel = ViewModelProvider(this)[MovieListViewModel::class.java]
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getData()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                adapterCategory1.setMovies(appState.movieCategory1)
                adapterCategory2.setMovies(appState.movieCategory2)
            }
            is AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                appState.error.localizedMessage?.let {
                    binding.mainContainerConstrainLayout.showErrorSnackBar(
                        it,
                        getString(R.string.reload)
                    )
                }
            }
        }
    }

    private fun View.showErrorSnackBar(
        text: String,
        actionText: String,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length)
            .setAction(actionText) { viewModel.getData() }
            .show()
    }

    interface OnItemViewClickListener {
        fun inItemViewClick(movie: Movie)
    }

    companion object {
        fun newInstance() =
            MovieListFragment()
    }
}