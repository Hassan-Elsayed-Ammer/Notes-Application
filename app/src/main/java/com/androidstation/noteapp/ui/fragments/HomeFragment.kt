package com.androidstation.noteapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidstation.noteapp.db.NoteDataBase
import com.androidstation.noteapp.adapter.NoteAdapter
import com.androidstation.noteapp.databinding.FragmentHomeBinding
import com.androidstation.noteapp.ui.NoteViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]


        viewModel.getAllNotesUseCoroutine()


        return binding.root


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_view_note.setHasFixedSize(true)
        recycler_view_note.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        //Fitch The note
        launch {
            context?.let{
                val notes = NoteDataBase(it).getNoteDao().getAllNotes()
                recycler_view_note.adapter = NoteAdapter(notes)
            }
        }

        btn_add.setOnClickListener(){
            val action = com.androidstation.noteapp.ui.HomeFragmentDirections.actionAddNote()
            Navigation.findNavController(it).navigate(action)
        }

    }

}