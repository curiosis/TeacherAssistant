package com.example.teacherassistant.viewModel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.model.entities.Course

class CoursesNInSAdapter(

        var courses: LiveData<List<Course>>,
        var addButton: (course: Course) -> Unit):
        RecyclerView.Adapter<CoursesNInSAdapter.CoursesNInSHolder>() {

    class CoursesNInSHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesNInSHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_add_c_to_s_row, parent, false)
        return CoursesNInSHolder(view)
    }

    override fun onBindViewHolder(holder: CoursesNInSHolder, position: Int) {

        val addButton = holder.view.findViewById<Button>(R.id.addCtoSBtn)
        val courseName = holder.view.findViewById<TextView>(R.id.addCtoSNameTV)

        courseName.text = courses.value?.get(position)?.name

        addButton.setOnClickListener {
            val thisCourse = courses.value?.get(position)
            if(thisCourse != null)
                addButton(thisCourse)
        }
    }

    override fun getItemCount(): Int = courses.value?.size ?: 0
}