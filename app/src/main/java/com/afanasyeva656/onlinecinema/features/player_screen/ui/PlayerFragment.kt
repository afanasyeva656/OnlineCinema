package com.afanasyeva656.onlinecinema.features.player_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.afanasyeva656.onlinecinema.databinding.FragmentPlayerBinding
import com.afanasyeva656.onlinecinema.features.about_movie_screen.ui.AboutMovieFragment
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.Util
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class PlayerFragment : Fragment() {
    private lateinit var binding: FragmentPlayerBinding
    private var player: ExoPlayer? = null

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    companion object {
        const val MOVIE_URL_KEY = "movie_url"

        fun newInstance(movieUrl: String): PlayerFragment = PlayerFragment().apply {
            arguments = bundleOf(MOVIE_URL_KEY to movieUrl)
        }
    }

//    private val movieUrl: String by lazy { parametersOf(arguments?.getParcelable(MOVIE_URL_KEY)) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }


    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    override fun onDetach() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDetach()
    }

    private fun initializePlayer() {
        player = get()
        binding.playerView.player = player
//        val mediaItem = MediaItem.fromUri(movieUrl)
//        player?.setMediaItem(mediaItem)
    }

    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentMediaItemIndex
            playWhenReady = this.playWhenReady
            release()
        }

        player = null
    }
}