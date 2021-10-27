package com.afanasyeva656.onlinecinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(MoviesListFragment())
    }

    private fun setFragment(fragment: Fragment) {
        // commit обязателен
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment).commit()
    }
}