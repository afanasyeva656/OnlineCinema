package com.afanasyeva656.onlinecinema.features.movies_list_screen.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.afanasyeva656.onlinecinema.R
import com.afanasyeva656.onlinecinema.databinding.FragmentMoviesListBinding
import com.afanasyeva656.onlinecinema.features.movies_list_screen.ui.adapter.MoviesAdapter
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {
    private val viewModel by viewModel<MoviesListViewModel>()
    private val requestOptions: RequestOptions by inject()
    private val adapter by lazy {
        MoviesAdapter(requestOptions, listOf()) {
            viewModel.processUiEvent(
                UiEvent.OnMovieClicked(it)
            )
        }
    }
    private val binding: FragmentMoviesListBinding by viewBinding(
        FragmentMoviesListBinding::bind
    )

    companion object {
        fun newInstance(): MoviesListFragment = MoviesListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.successMoviesListFragment.rvMovies
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    private fun render(state: ViewState) {
        updateProgressBar(state.isLoading)
        renderSuccess(state)
        renderError(state.errorMessageForUser)
    }

    private fun renderSuccess(state: ViewState) {
        if (state.moviesList.isNotEmpty()) {
            adapter.updateMovies(state.moviesList)
            binding.errorMoviesListFragment.root.visibility = View.GONE
            binding.successMoviesListFragment.root.visibility = View.VISIBLE
        }
    }

    private fun updateProgressBar(isLoading: Boolean) {
        binding.pbMoviesList.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun renderError(errorMessage: Int?) {
        errorMessage?.let {
            binding.errorMoviesListFragment.root.visibility = View.VISIBLE
            binding.errorMoviesListFragment.tvErrorTitle.text = getString(errorMessage)
            binding.errorMoviesListFragment.bTryAgain.setOnClickListener {
                viewModel.processUiEvent(UiEvent.OnTryAgainClicked)
                binding.errorMoviesListFragment.root.visibility = View.GONE
            }
        }
    }
}