package com.androidstation.noteapp.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.androidstation.noteapp.R
import com.androidstation.noteapp.databinding.FragmentAddNoteBinding
import com.androidstation.noteapp.db.Note
import com.androidstation.noteapp.db.NoteDataBase
import com.androidstation.noteapp.utils.toast
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch


class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding
    private var note: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNoteBinding.inflate(inflater,container,false)
        //set option menu function in on create
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            note = com.androidstation.noteapp.ui.AddNoteFragmentArgs.fromBundle(it).note
            et_title.setText(note?.title)
            et_note.setText(note?.note)

        }

        btn_save.setOnClickListener() {view->
            val noteTitle = et_title.text.toString().trim()
            val noteBody =  et_note.text.toString().trim()

            if (noteTitle.isEmpty()) {

                et_title.error = "title required"
                et_title.requestFocus()
                return@setOnClickListener
            }
            if (noteBody.isEmpty()) {

                et_note.error = "note required"
                et_note.requestFocus()
                return@setOnClickListener
            }

            launch {
                context?.let {
                    val mNote = Note(noteTitle, noteBody)
                    if (note == null){

                        NoteDataBase(it).getNoteDao().addNote(mNote)
                        it.toast("Note Saved")
                    }else{
                        //Update note
                        mNote.id = note!!.id
                        NoteDataBase(it).getNoteDao().updateNote(mNote)
                        it.toast("Note Updated")
                    }

                    val action =
                        com.androidstation.noteapp.ui.AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view).navigate(action)
                }
            }
        }
    }

    //delete function
    private fun deleteNote() {
        AlertDialog.Builder(context).apply {
            setTitle("Are You Sure ?")
            setMessage("You Can Not Undo This operation")
            setPositiveButton( "Yes"){ _ , _ ->
                launch {
                    //delete from database
                    NoteDataBase(context).getNoteDao().deleteNote(note!!)
                    val action =
                        com.androidstation.noteapp.ui.AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }
            setNegativeButton("No"){ _ , _ ->
            }
        }.create().show()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> if (note != null) deleteNote() else context?.toast("Cannot Delete")
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }
}