package com.afanasyeva656.onlinecinema.features.about_movie_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.afanasyeva656.onlinecinema.R
import com.afanasyeva656.onlinecinema.databinding.FragmentAboutMovieBinding
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AboutMovieFragment : Fragment() {
    companion object {
        private const val EXTRA_MOVIE = "extra_movie"
        fun newInstance(movieModel: MovieDomainModel): AboutMovieFragment {
            return AboutMovieFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_MOVIE, movieModel)
                }
            }
        }
    }

    private lateinit var binding: FragmentAboutMovieBinding
    private val requestOptions by inject<RequestOptions>()

    //    TODO: переделать
    private val viewModel by viewModel<AboutMovieViewModel>() {
        parametersOf(
            arguments?.getParcelable(
                EXTRA_MOVIE
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    private fun render(state: ViewState) {
        binding.tvMovieTitle.text = state.movieDomainModel.title
        binding.tvMovieReleaseDate.text = state.movieDomainModel.releaseDate
        binding.tvMovieOverview.text = state.movieDomainModel.overview
        Glide
            .with(binding.root)
            .load(state.movieDomainModel.posterPath)
            .apply(requestOptions)
            .into(binding.ivMoviePoster)

        binding.bWatchMovie.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnVideoClicked)
        }
    }
}