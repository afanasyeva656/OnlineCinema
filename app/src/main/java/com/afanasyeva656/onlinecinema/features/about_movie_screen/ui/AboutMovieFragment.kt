package com.afanasyeva656.onlinecinema.features.about_movie_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afanasyeva656.onlinecinema.databinding.FragmentAboutMovieBinding
import com.afanasyeva656.onlinecinema.databinding.FragmentTempItemBinding
import com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api.model.MovieModel
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutMovieFragment(
    private val movieModel: MovieDomainModel
) : Fragment() {
//    private lateinit var binding: FragmentAboutMovieBinding
    private lateinit var binding: FragmentTempItemBinding

    companion object {
        fun newInstance(movieModel: MovieDomainModel): AboutMovieFragment =
            AboutMovieFragment(movieModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        binding = FragmentAboutMovieBinding.inflate(inflater, container, false)
        binding = FragmentTempItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun render() {

    }
}