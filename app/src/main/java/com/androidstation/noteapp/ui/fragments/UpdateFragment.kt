package com.androidstation.noteapp.ui.fragments

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androidstation.noteapp.R
import com.androidstation.noteapp.databinding.FragmentUpdateBinding
import com.androidstation.noteapp.db.Note
import com.androidstation.noteapp.db.NoteDataBase
import com.androidstation.noteapp.repositories.NoteRepository
import com.androidstation.noteapp.ui.NoteViewModel
import com.androidstation.noteapp.ui.NoteViewModelFactory
import com.androidstation.noteapp.utils.toast
import com.google.android.material.snackbar.Snackbar


class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var currentNote: Note

    private lateinit var updateNoteViewModel: NoteViewModel
    private val args: UpdateFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setUpViewModel()

        updateNote()

        return binding.root
    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(
            NoteDataBase.getDataBase(requireContext())
        )
        val viewModelFactory = NoteViewModelFactory(Application(), noteRepository)
        updateNoteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]
    }

    //update
    private fun updateNote() {
        //get Current Note from Args
        currentNote = args.note!!
        //get current state for data by using object that saved in args
        binding.etTitleUpdate.setText(currentNote.title)
        binding.etBodyUpdate.setText(currentNote.body)

        binding.btnDone.setOnClickListener {
            //create new note object
            val noteTitle = binding.etTitleUpdate.text.toString().trim()
            val noteBody = binding.etBodyUpdate.text.toString().trim()

            if (noteTitle.isNotEmpty() && noteBody.isNotEmpty()) {
                val note = Note(currentNote.id, noteTitle, noteBody)
                updateNoteViewModel.updateNote(note)

                showSnackBar()
                goToHomeFragment()
            } else {
                activity?.toast("please enter note title...!")
            }
        }


    }

    private fun goToHomeFragment() {
        val action = UpdateFragmentDirections.actionUpdateFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun showSnackBar() {
        Snackbar.make(requireView(), "Note Edited Successfully :)", Snackbar.LENGTH_LONG).show()
    }

    //delete function
    private fun deleteNote() {
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Are you Sure you Want Delete This Note!")
            setPositiveButton("DELETE") { _,_->
                updateNoteViewModel.delete(currentNote)
                goToHomeFragment()
                activity?.toast("Note Deleted Successfully.. :)")
            }
            setNegativeButton("CANCEL" , null)
        }.create().show()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}