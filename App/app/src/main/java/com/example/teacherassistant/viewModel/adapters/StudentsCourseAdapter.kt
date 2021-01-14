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
import com.example.teacherassistant.model.entities.Student

class StudentsCourseAdapter(
        var students : LiveData<List<Student>>,
        var deleteButton: (student: Student) -> Unit,
        var currentStudentCh: (student: Student) -> Unit) :
        RecyclerView.Adapter<StudentsCourseAdapter.StudentCourseHolder>() {

    class StudentCourseHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentCourseHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.one_student_con_layout, parent, false) as View
        return StudentCourseHolder(view)
    }

    override fun onBindViewHolder(holder: StudentCourseHolder, position: Int) {

        val delButton = holder.view.findViewById<Button>(R.id.dButton)
        val studentSurname = holder.view.findViewById<TextView>(R.id.studentSTV)
        val studentName = holder.view.findViewById<TextView>(R.id.studentNTV)
        val marks = holder.view.findViewById<Button>(R.id.marksButton)

        studentSurname.text = students.value?.get(position)?.surname
        studentName.text = students.value?.get(position)?.name

        marks.setOnClickListener {
            view->view.findNavController().navigate(R.id.action_studentsCourseFragment_to_marksFragment2)
            val thisStudent = students.value?.get(position)
            if(thisStudent != null)
                currentStudentCh(thisStudent)
        }
    }

    override fun getItemCount(): Int = students.value?.size ?: 0
}