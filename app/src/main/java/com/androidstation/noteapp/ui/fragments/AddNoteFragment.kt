package com.androidstation.noteapp.ui.fragments

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
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
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch


class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private var currentNote: Note? = null

    private val args: AddNoteFragmentArgs by navArgs()

    private lateinit var addNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        //set option menu function in on create
        setHasOptionsMenu(true)
        setUpViewModel()

        // add not or add current note
        currentNote = args.note



        binding.btnSave.setOnClickListener {
            saveNote()
        }


        // when he click save that call save and updateTE FUN


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



//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        arguments?.let {
//            currentNote = com.androidstation.noteapp.ui.AddNoteFragmentArgs.fromBundle(it).note
//            et_title.setText(currentNote?.title)
//            et_body.setText(currentNote?.body)
//
//        }
//
//        btn_save.setOnClickListener() { view ->
//            val noteTitle = et_title.text.toString().trim()
//            val noteBody = et_body.text.toString().trim()
//
//            if (noteTitle.isEmpty()) {
//                et_title.error = "title required"
//                et_title.requestFocus()
//                return@setOnClickListener
//            }
//            if (noteBody.isEmpty()) {
//                et_body.error = "note required"
//                et_body.requestFocus()
//                return@setOnClickListener
//            }
//
//            launch {
//                context?.let {
//                    val mNote = Note(noteTitle, noteBody)
//                    if (currentNote == null) {
//
//                        NoteDataBase(it).getNoteDao().addNote(mNote)
//                        it.toast("Note Saved")
//                    } else {
//                        //Update note
//                        mNote.id = currentNote!!.id
//                        NoteDataBase(it).getNoteDao().updateNote(mNote)
//                        it.toast("Note Updated")
//                    }
//
//                }
//            }
//        }
//    }



    private fun goToHomeFragment() {
        val action = AddNoteFragmentDirections.actionSaveNote()
        findNavController().navigate(action)
    }

    private fun showSnackBar() {
        Snackbar.make(requireView(), "Note Save Successfully!", Snackbar.LENGTH_LONG).show()
    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.delete -> {
//                if (currentNote != null) deleteNote() else context?.toast("Cannot Delete")
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }
}