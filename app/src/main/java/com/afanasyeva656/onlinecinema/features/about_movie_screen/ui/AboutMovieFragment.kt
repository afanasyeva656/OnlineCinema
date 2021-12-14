package com.afanasyeva656.onlinecinema.features.about_movie_screen.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.afanasyeva656.onlinecinema.R
import com.afanasyeva656.onlinecinema.databinding.FragmentAboutMovieBinding
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AboutMovieFragment : Fragment(R.layout.fragment_about_movie) {
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

    private val binding: FragmentAboutMovieBinding by viewBinding(FragmentAboutMovieBinding::bind)
    private val requestOptions by inject<RequestOptions>()

    private val viewModel by viewModel<AboutMovieViewModel> {
        parametersOf(
            arguments?.getParcelable(
                EXTRA_MOVIE
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    private fun render(state: ViewState) {
        with(binding) {
            tvMovieTitle.text = state.movieDomainModel.title
            tvMovieReleaseDate.text = state.movieDomainModel.releaseDate
            tvMovieOverview.text = state.movieDomainModel.overview
            Glide
                .with(root)
                .load(state.movieDomainModel.posterPath)
                .apply(requestOptions)
                .into(ivMoviePoster)

            bWatchMovie.setOnClickListener { viewModel.processUiEvent(UiEvent.OnVideoClicked) }
        }
    }
}