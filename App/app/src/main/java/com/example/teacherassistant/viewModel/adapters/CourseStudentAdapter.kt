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
import com.example.teacherassistant.model.entities.Course

class CourseStudentAdapter (
        var courses : LiveData<List<Course>>,
        var deleteButton: (course: Course) -> Unit,
        var currentCourseCh: (course: Course) -> Unit) :
        RecyclerView.Adapter<CourseStudentAdapter.CourseStudentHolder>() {

    class CourseStudentHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseStudentHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.one_course_con_layout, parent, false) as View
        return CourseStudentHolder(view)
    }

    override fun onBindViewHolder(holder: CourseStudentHolder, position: Int) {

        val courseName = holder.view.findViewById<TextView>(R.id.crsNameTView)
        val marks = holder.view.findViewById<Button>(R.id.marButton)
        val delButton = holder.view.findViewById<Button>(R.id.delButton)

        courseName.text = courses.value?.get(position)?.name

        marks.setOnClickListener {
            view->view.findNavController().navigate(R.id.action_courseStudentFragment_to_marksFragment2)
            val thisCourse = courses.value?.get(position)
            if(thisCourse != null)
                currentCourseCh(thisCourse)
        }

        delButton.setOnClickListener {
            val thisCourse = courses.value?.get(position)
            if(thisCourse != null)
                deleteButton(thisCourse)
        }
    }
    override fun getItemCount(): Int = courses.value?.size ?: 0
}