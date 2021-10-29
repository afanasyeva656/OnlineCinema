package com.afanasyeva656.onlinecinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.afanasyeva656.onlinecinema.features.movies_list_screen.ui.MoviesListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(MoviesListFragment.newInstance())
    }

    private fun setFragment(fragment: Fragment) {
        // commit обязателен
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment).commit()
    }
}