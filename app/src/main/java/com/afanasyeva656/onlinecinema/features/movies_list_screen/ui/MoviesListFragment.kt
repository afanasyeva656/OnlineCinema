package com.afanasyeva656.onlinecinema.features.movies_list_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afanasyeva656.onlinecinema.R
import com.afanasyeva656.onlinecinema.databinding.FragmentMoviesListBinding
import com.afanasyeva656.onlinecinema.features.movies_list_screen.ui.adapter.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment : Fragment() {
    private val viewModel by viewModel<MoviesListViewModel>()
    private val adapter by lazy { MoviesAdapter(listOf()) }
    private lateinit var binding: FragmentMoviesListBinding

    companion object {
        fun newInstance(): MoviesListFragment = MoviesListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.rvMovies
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    private fun render(state: ViewState) {
        adapter.updateMovies(state.moviesList)
    }
}