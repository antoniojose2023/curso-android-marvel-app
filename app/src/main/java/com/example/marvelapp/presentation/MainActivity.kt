package com.example.marvelapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

       navController = navHostFragment.navController
       appBarConfiguration = AppBarConfiguration(
           setOf(R.id.charactersFragment, R.id.favoritesFragment, R.id.aboutFragment)
       )

      binding.bottonNavigationView.setupWithNavController(navController)

      binding.toolbar.setupWithNavController(navController, appBarConfiguration)

      // codigo opcional - caso precise tirar os botoes de voltar
      navController.addOnDestinationChangedListener { _, destination, _ ->
          val isTopLevelDestination = appBarConfiguration.topLevelDestinations.contains(destination.id)
          if (!isTopLevelDestination) {
                binding.toolbar.setNavigationIcon(R.drawable.ic_back)
          }
      }
    }
}
