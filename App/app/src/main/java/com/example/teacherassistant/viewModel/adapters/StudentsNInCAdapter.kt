package com.example.teacherassistant.viewModel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.model.entities.Student

class StudentsNInCAdapter(
        var students: LiveData<List<Student>>,
        var addButton: (student: Student) -> Unit):
        RecyclerView.Adapter<StudentsNInCAdapter.StudentsNInHolder>() {

    class StudentsNInHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsNInHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_add_s_to_c_rows, parent, false)
        return StudentsNInHolder(view)
    }

    override fun onBindViewHolder(holder: StudentsNInHolder, position: Int) {

        val addButton = holder.view.findViewById<Button>(R.id.addStoCBtn)
        val studentSurname = holder.view.findViewById<TextView>(R.id.addStoCSurnameTV)
        val studentName = holder.view.findViewById<TextView>(R.id.addStoCNameTV)

        studentSurname.text = students.value?.get(position)?.surname
        studentName.text = students.value?.get(position)?.name

        addButton.setOnClickListener {
            val thisStudent = students.value?.get(position)
            if(thisStudent != null)
                addButton(thisStudent)
        }
    }
    override fun getItemCount(): Int = students.value?.size ?: 0
}