package com.grupo7.moneychange.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.grupo7.moneychange.R
import com.grupo7.moneychange.repository.CountryRepository
import kotlinx.android.synthetic.main.conversion_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController: NavController = findNavController(R.id.navHostFragment)
        appBarConfiguration =
            AppBarConfiguration.Builder(R.id.conversionFragment)
                .build()
        setupActionBarWithNavController(navController, appBarConfiguration)

        GlobalScope.launch(Dispatchers.Main) {
            val countryRepository = CountryRepository(this@MainActivity)
            val text = "estás en ${countryRepository.currentCountry()}"
            location_text?.text = text
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navHostFragment).navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }


}
