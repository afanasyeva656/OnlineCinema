package com.afanasyeva656.onlinecinema.features.player_screen.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.afanasyeva656.onlinecinema.R
import com.afanasyeva656.onlinecinema.databinding.FragmentPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.util.Util
import org.koin.android.ext.android.get

class PlayerFragment : Fragment(R.layout.fragment_player) {
    private val binding: FragmentPlayerBinding by viewBinding(FragmentPlayerBinding::bind)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        hideSystemUI()
//        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
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
        val movieUrl: String = requireArguments().getString(MOVIE_URL_KEY)!!

        player?.let {
            binding.playerView.player = it
            it.setMediaItem(MediaItem.fromUri(movieUrl))
            it.playWhenReady = playWhenReady
            it.seekTo(currentWindow, playbackPosition)
            it.prepare()
            it.addListener(object : Player.Listener {
                override fun onPlayerStateChanged(playWhenReady: Boolean,playbackState: Int) {
                    when (playbackState) {
                        Player.STATE_BUFFERING -> {
                            binding.pbPlayer.isVisible = true
                        }
                        Player.STATE_READY -> {
                            binding.pbPlayer.isVisible = false
                        }
                    }
                }
            })
        }
    }

    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentMediaItemIndex
            playWhenReady = this.playWhenReady
            this.release()
        }

        player = null
    }

    private fun hideSystemUI() {
        requireActivity().actionBar?.hide()
        if (Build.VERSION.SDK_INT >= 30) {
            requireActivity().window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            }
        } else {
            // Enables regular immersive mode.
            // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
            // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            requireActivity().window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }
}