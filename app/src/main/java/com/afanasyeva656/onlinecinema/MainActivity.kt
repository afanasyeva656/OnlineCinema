package com.afanasyeva656.onlinecinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.afanasyeva656.onlinecinema.databinding.ActivityMainBinding
import com.afanasyeva656.onlinecinema.features.movies_list_screen.ui.MoviesListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(MoviesListFragment.newInstance())
    }

    private fun setFragment(fragment: Fragment) {
        // commit обязателен
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment).commit()
    }
}