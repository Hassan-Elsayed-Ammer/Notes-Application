package com.androidstation.noteapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.androidstation.noteapp.R
import com.androidstation.noteapp.databinding.ActivityMainBinding
import com.androidstation.noteapp.db.NoteDataBase
import com.androidstation.noteapp.repositories.NoteRepository

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NoteViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        // prepare repository
        val repository = NoteRepository(NoteDataBase(this))

        //prepare view model factory
        val viewModelFactory = NoteViewModelFactory(application, repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]
    }

    //Function to handle Back button
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.fragment),
            null
        )
    }

}