package com.androidstation.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.androidstation.noteapp.databinding.NoteLayoutBinding
import com.androidstation.noteapp.db.Note
import com.androidstation.noteapp.ui.fragments.HomeFragmentDirections

class NoteAdapter(
    private val notes: List<Note>
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: NoteLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NoteViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.tvTitle.text = note.title
        holder.binding.tvNote.text = note.body

        //setOnclick  to update currant note
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(note)
            action.note = notes[position]
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = notes.size


}


