package com.androidstation.noteapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.androidstation.noteapp.R
import com.androidstation.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolBar = binding.toolbar



        // create reference from nave host fragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment)
        // reassign the navController to nav host because nav host has already nav Controller to control on fragments that will appeared in feature
        navController = navHostFragment!!.findNavController()

        setSupportActionBar(toolBar)
        setupActionBarWithNavController(navController)


    }

    //Function to handle Back button
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.fragment),
            null
        )
    }

}