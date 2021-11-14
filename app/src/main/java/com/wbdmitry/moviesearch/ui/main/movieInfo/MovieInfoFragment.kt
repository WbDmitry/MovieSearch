package com.wbdmitry.moviesearch.ui.main.movieInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wbdmitry.moviesearch.databinding.FragmentMovieInfoBinding
import com.wbdmitry.moviesearch.model.entity.Movie
import kotlinx.android.synthetic.main.fragment_movie_info.*

class MovieInfoFragment : Fragment() {
    private var _binding: FragmentMovieInfoBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val BUNDLE_EXTRA = "movie"

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
        _binding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Movie>(BUNDLE_EXTRA)?.let {
            header_poster_movie_info_image_view.setImageResource(it.poster)
            title_movie_info_text_view.text = it.title
            mini_poster_movie_info_image_view.setImageResource(it.poster)
            description_movie_text_view.text = it.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}