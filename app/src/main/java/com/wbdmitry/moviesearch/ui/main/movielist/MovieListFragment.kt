package com.wbdmitry.moviesearch.ui.main.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wbdmitry.moviesearch.AppSetting
import com.wbdmitry.moviesearch.R
import com.wbdmitry.moviesearch.databinding.FragmentMovieListBinding
import com.wbdmitry.moviesearch.model.AppState
import com.wbdmitry.moviesearch.model.entity.Movie
import com.wbdmitry.moviesearch.model.repository.RepositoryImpl
import com.wbdmitry.moviesearch.model.repository.retrofit.RemoteDataSource
import com.wbdmitry.moviesearch.ui.main.adapters.MovieListAdapter
import com.wbdmitry.moviesearch.ui.main.movieInfo.MovieInfoFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieListFragment : Fragment(), CoroutineScope by MainScope() {
    private lateinit var binding: FragmentMovieListBinding
    private val viewModel: MovieListViewModel by viewModel {
        parametersOf(RepositoryImpl(RemoteDataSource()))
    }
    private var adapter: MovieListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieListRecyclerView.adapter = adapter
        viewModel.liveData.observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getNewDataFromServer()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Loading ->
                movieListFragmentLoadingLayout.visibility = View.VISIBLE
            is AppState.Success -> {
                movieListFragmentLoadingLayout.visibility = View.GONE
                adapter = MovieListAdapter(object : OnItemViewClickListener {
                    override fun inItemViewClick(movie: Movie) {
                        val fragmentManager = activity?.supportFragmentManager
                        fragmentManager?.let { manager ->
                            val bundle = Bundle().apply {
                                putInt(MovieInfoFragment.BUNDLE_EXTRA, movie.id)
                            }
                            manager.beginTransaction()
                                .add(R.id.main_container, MovieInfoFragment.newInstance(bundle))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                        }
                    }
                }
                ).apply {
                    setMovies(if (AppSetting.adultCheckBoxCondition) {
                        appState.movieData.filter { it.adult }
                    } else {
                        appState.movieData
                    })
                }
                movieListRecyclerView.adapter = adapter
            }
            is AppState.Error -> {
                movieListFragmentLoadingLayout.visibility = View.GONE
                Toast.makeText(
                    context,
                    R.string.error,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    interface OnItemViewClickListener {
        fun inItemViewClick(movie: Movie)
    }

    companion object {
        fun newInstance() =
            MovieListFragment()
    }
}