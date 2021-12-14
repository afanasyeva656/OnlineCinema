package com.afanasyeva656.onlinecinema

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afanasyeva656.onlinecinema.base.navigation.BackButtonListener
import com.afanasyeva656.onlinecinema.base.navigation.Screens
import com.afanasyeva656.onlinecinema.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val router: Router by inject()
    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator = object : AppNavigator(this, R.id.actMain) {
        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigatorHolder.setNavigator(navigator)
        router.newRootScreen(Screens.MoviesListScreen())
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.actMain)
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()) {
            return
        } else {
            super.onBackPressed()
        }
    }
}