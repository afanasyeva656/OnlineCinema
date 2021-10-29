package com.afanasyeva656.onlinecinema.features.movies_list_screen.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.afanasyeva656.onlinecinema.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment: Fragment() {
    private val viewModel by viewModel<MoviesListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    private fun render(state: ViewState) {
        view?.findViewById<TextView>(R.id.tvTitle)?.text = state.moviesList.toString()
    }
}