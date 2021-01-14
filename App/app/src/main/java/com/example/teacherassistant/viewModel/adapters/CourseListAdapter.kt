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

class CourseListAdapter(
        var courses: LiveData<List<Course>>,
        val deleteButton: (course: Course) -> Unit,
        val currentCourseCh: (course: Course) -> Unit):
        RecyclerView.Adapter<CourseListAdapter.CourseHolder>(){

    class CourseHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseListAdapter.CourseHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_course_row, parent, false) as View
        return CourseHolder(view)
    }

    override fun onBindViewHolder(holder: CourseListAdapter.CourseHolder, position: Int) {

        val editButton = holder.view.findViewById<Button>(R.id.crsEdit)
        val delButton = holder.view.findViewById<Button>(R.id.crsDel)
        val crsStudent = holder.view.findViewById<Button>(R.id.crsStudentsBtn)
        val courseName = holder.view.findViewById<TextView>(R.id.crsNameTV)

        courseName.text = courses.value?.get(position)?.name

        editButton.setOnClickListener {
                view->view.findNavController().navigate(
            R.id.action_coursesFragment_to_addCourseFragment)
            val thisCourse = courses.value?.get(position)
            if(thisCourse != null){
                currentCourseCh(thisCourse)
            }
        }

        delButton.setOnClickListener {
            val thisCourse = courses.value?.get(position)
            if(thisCourse != null){
                deleteButton(thisCourse)
            }
        }

        crsStudent.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_coursesFragment_to_studentsCourseFragment)
            val thisCourse = courses.value?.get(position)
            if(thisCourse != null){
                currentCourseCh(thisCourse)
            }
        }
    }

    override fun getItemCount(): Int = courses.value?.size ?: 0
}