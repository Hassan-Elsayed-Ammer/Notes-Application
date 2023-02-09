package com.androidstation.noteapp.ui.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidstation.noteapp.adapter.NoteAdapter
import com.androidstation.noteapp.databinding.FragmentHomeBinding
import com.androidstation.noteapp.db.Note
import com.androidstation.noteapp.db.NoteDataBase
import com.androidstation.noteapp.repositories.NoteRepository
import com.androidstation.noteapp.ui.NoteViewModel
import com.androidstation.noteapp.ui.NoteViewModelFactory


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpViewModel()

        binding.btnAdd.setOnClickListener {
            goToAddNoteFragment()
        }

        homeViewModel.getAllNotes().observe(viewLifecycleOwner) { noteList ->
            //add data in recyclerView and show recycler view
            setUpRecyclerView(noteList)

        }

        return binding.root
    }

    private fun setUpRecyclerView(noteList: List<Note>) {
        if (noteList.isNotEmpty()) {
            binding.cardView.visibility = View.GONE
            binding.recyclerViewNote.apply {
                visibility = View.VISIBLE
                layoutManager = StaggeredGridLayoutManager(
                    2, StaggeredGridLayoutManager.VERTICAL
                )
                setHasFixedSize(true)
                adapter = NoteAdapter(noteList)
            }
        } else {
            binding.cardView.visibility = View.VISIBLE
            binding.recyclerViewNote.visibility = View.GONE
        }
    }

    private fun goToAddNoteFragment() {
        val action = HomeFragmentDirections.actionAddNote()
        findNavController().navigate(action)
    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(
            NoteDataBase.getDataBase(requireContext())
        )
        val viewModelFactory = NoteViewModelFactory(Application(), noteRepository)

        homeViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]
    }

}