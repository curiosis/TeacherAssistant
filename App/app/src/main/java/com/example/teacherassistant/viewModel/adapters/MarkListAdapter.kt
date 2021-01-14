package com.example.teacherassistant.viewModel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.model.entities.Mark

class MarkListAdapter(
        var marks: LiveData<List<Mark>>?,
        val deleteButton: (mark: Mark) -> Unit,
        val currentMarkCh: (mark: Mark) -> Unit):
        RecyclerView.Adapter<MarkListAdapter.MarkHolder>() {

    class MarkHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.one_mark_row, parent, false) as View
        return MarkHolder(view)
    }

    override fun onBindViewHolder(holder: MarkHolder, position: Int) {

        val markTV = holder.view.findViewById<TextView>(R.id.markTView)
        val noteTV = holder.view.findViewById<TextView>(R.id.noteTView)

        markTV.text = marks?.value?.get(position)?.mark.toString()
        noteTV.text = marks?.value?.get(position)?.note

        val editButton = holder.view.findViewById<Button>(R.id.editButton)
        editButton.setOnClickListener {
            view->view.findNavController().navigate(R.id.action_marksFragment_to_addMarkFragment)
            val thisMark = marks?.value?.get(position)
            if(thisMark != null)
                currentMarkCh(thisMark)
        }

        val delButton = holder.view.findViewById<Button>(R.id.deleteButton)
        delButton.setOnClickListener {
            val thisMark = marks?.value?.get(position)
            if(thisMark != null)
                deleteButton(thisMark)
        }
    }

    override fun getItemCount(): Int = marks?.value?.size ?: 0
}