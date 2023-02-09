package com.androidstation.noteapp.ui.fragments

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androidstation.noteapp.R
import com.androidstation.noteapp.databinding.FragmentAddNoteBinding
import com.androidstation.noteapp.db.Note
import com.androidstation.noteapp.db.NoteDataBase
import com.androidstation.noteapp.repositories.NoteRepository
import com.androidstation.noteapp.ui.NoteViewModel
import com.androidstation.noteapp.ui.NoteViewModelFactory
import com.androidstation.noteapp.utils.toast
import com.google.android.material.snackbar.Snackbar


class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private var currentNote: Note? = null

    private lateinit var addNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        //set option menu function in on create
        setHasOptionsMenu(true)
        setUpViewModel()

        binding.btnSave.setOnClickListener {
            saveNote()
        }

        return binding.root
    }

    private fun setUpViewModel(){
        val noteRepository = NoteRepository(
            NoteDataBase.getDataBase(requireContext())
        )

        val viewModelFactory = NoteViewModelFactory(Application(),noteRepository)
        addNoteViewModel =  ViewModelProvider(this ,viewModelFactory)[NoteViewModel::class.java]
    }


    //Add and Save Note
    private fun saveNote() {
        val noteTitle = binding.etTitle.text.toString().trim()
        val noteBody = binding.etBody.text.toString().trim()

        if (noteTitle.isNotEmpty() && noteBody.isNotEmpty()) {
            val note = Note(0,noteTitle, noteBody)
            addNoteViewModel.addNote(note)
            showSnackBar()
            goToHomeFragment()
        } else {
            activity?.toast("please enter note title...!")
        }

    }


    private fun goToHomeFragment() {
        val action = AddNoteFragmentDirections.actionSaveNote()
        findNavController().navigate(action)
    }

    private fun showSnackBar() {
        Snackbar.make(requireView(), "Note Save Successfully!", Snackbar.LENGTH_LONG).show()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }
}