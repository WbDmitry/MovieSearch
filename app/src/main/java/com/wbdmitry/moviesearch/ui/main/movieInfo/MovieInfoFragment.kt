package com.wbdmitry.moviesearch.ui.main.movieInfo

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.wbdmitry.moviesearch.R
import com.wbdmitry.moviesearch.databinding.FragmentMovieInfoBinding
import com.wbdmitry.moviesearch.model.AppState
import com.wbdmitry.moviesearch.model.repository.RepositoryImpl
import com.wbdmitry.moviesearch.model.repository.retrofit.RemoteDataSource
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieInfoFragment : Fragment() {
    private lateinit var binding: FragmentMovieInfoBinding
    private val viewModel: MovieInfoViewModel by viewModel {
        parametersOf(RepositoryImpl(RemoteDataSource()))
    }
    private var movieId: Int = 0

    companion object {
        const val BUNDLE_EXTRA = "movie_id"
        private const val MOVIE_ID = 0

        fun newInstance(bundle: Bundle): MovieInfoFragment {
            val fragment = MovieInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(BUNDLE_EXTRA, MOVIE_ID)?.let {
            movieId = it
            viewModel.liveData.observe(viewLifecycleOwner, { appState ->
                renderData(appState)
            })
            viewModel.getMovieInfoFromRemoteSource(it)
        }
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Loading ->
                movieInfoFragmentLoadingLayout.visibility = View.VISIBLE
            is AppState.Success -> {
                movieInfoFragmentLoadingLayout.visibility = View.GONE
                miniPosterMovieInfoImageView.load("https://image.tmdb.org/t/p/original" + appState.movieData.first().poster_path)
                titleMovieInfoTextView.text = appState.movieData.first().title
                descriptionMovieTextView.text = appState.movieData.first().overview
            }
            is AppState.Error -> {
                movieInfoFragmentLoadingLayout.visibility = View.GONE
                Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}