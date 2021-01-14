package com.example.teacherassistant.viewModel.adapters

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.model.entities.Student
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.teacherassistant.R

class StudentListAdapter(
        var students : LiveData<List<Student>>,
        var deleteButton: (student: Student) -> Unit,
        var currentStudentCh: (student: Student) -> Unit) :
        RecyclerView.Adapter<StudentListAdapter.StudentHolder>() {

    class StudentHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_student_row, parent, false) as View
        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {

        val editButton = holder.view.findViewById<Button>(R.id.stEdit)
        val delButton = holder.view.findViewById<Button>(R.id.stDel)
        val stCourses = holder.view.findViewById<Button>(R.id.stCoursesBtn)
        val studentSurname = holder.view.findViewById<TextView>(R.id.stSurnameTV)
        val studentName = holder.view.findViewById<TextView>(R.id.stNameTV)

        studentSurname.text = students.value?.get(position)?.surname
        studentName.text = students.value?.get(position)?.name

        editButton.setOnClickListener {
            view->view.findNavController().navigate(
            R.id.action_studentsFragment_to_addStudentFragment)
            val thisStudent = students.value?.get(position)
            if(thisStudent != null){
                currentStudentCh(thisStudent)
            }
        }

        delButton.setOnClickListener {
            val thisStudent = students.value?.get(position)
            if(thisStudent != null){
                deleteButton(thisStudent)
            }
        }

        stCourses.setOnClickListener {
            view->view.findNavController().navigate(R.id.action_studentsFragment_to_courseStudentFragment)
            val thisStudent = students.value?.get(position)
            if(thisStudent != null){
                currentStudentCh(thisStudent)
            }
        }
    }

    override fun getItemCount(): Int = students.value?.size ?: 0
}