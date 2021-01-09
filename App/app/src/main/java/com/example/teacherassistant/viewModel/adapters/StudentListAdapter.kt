package com.example.teacherassistant.viewModel.adapters

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.model.entities.Student
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.teacherassistant.R

class StudentListAdapter(
    var students : LiveData<List<Student>>,
    var onClickDelete: (student: Student) -> Unit,
    var currentStudentCh: (student: Student) -> Unit) :
        RecyclerView.Adapter<StudentListAdapter.StudentHolder>() {

    class StudentHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_item_row, parent, false) as View
        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {

        val editButton = holder.view.findViewById<Button>(R.id.editBtn)
        editButton.setOnClickListener {
            view->view.findNavController().navigate(
            R.id.action_studentsFragment_to_addStudentFragment)
            val thisElement = students.value?.get(position)
            if(thisElement != null){
                currentStudentCh(thisElement)
            }
        }

        val delButton = holder.view.findViewById<Button>(R.id.delBtn)
        delButton.setOnClickListener {
            val thisElement = students.value?.get(position)
            if(thisElement != null){
                onClickDelete(thisElement)
            }
        }

        val elementButton = holder.view.findViewById<Button>(R.id.itemBtn)
        elementButton.text = "${students.value?.get(position)?.name} ${students.value?.get(position)?.surname}"
        elementButton.setOnClickListener {
            view->view.findNavController().navigate(R.id.)
        }


    }

    override fun getItemCount(): Int = students.value?.size ?: 0
}