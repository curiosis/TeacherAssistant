package com.example.teacherassistant.viewModel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.model.entities.Mark
import com.example.teacherassistant.viewModel.vm.CourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentCourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentViewModel

class ReportAdapter(
        var marks: LiveData<List<Mark>>?,
        private val studentCourseViewModel: StudentCourseViewModel,
        private val studentViewModel: StudentViewModel,
        private val courseViewModel: CourseViewModel): RecyclerView.Adapter<ReportAdapter.ReportHolder>() {

    class ReportHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportAdapter.ReportHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.one_report_row, parent, false) as View
        return ReportHolder(view)
    }

    override fun onBindViewHolder(holder: ReportHolder, position: Int) {

        val currentStudentCourseId =
                marks?.value?.get(position)?.studentCourseId
        val currentStudentCourse =
                studentCourseViewModel.con.value?.find {
                    studentCourse -> studentCourse.id == currentStudentCourseId
                }
        val currentStudent =
                studentViewModel.students.value?.find {
                    student -> student.id == currentStudentCourse?.studentId
                }
        val currentCourse =
                courseViewModel.courses.value?.find {
                    course -> course.id == currentStudentCourse?.courseId
                }

        val courseTView = holder.view.findViewById<TextView>(
                R.id.courseTv
        )
        val studentSurnameTView = holder.view.findViewById<TextView>(
                R.id.studentSurnameTv
        )
        val studentNameTView = holder.view.findViewById<TextView>(
                R.id.studentNameTv
        )
        val markTView = holder.view.findViewById<TextView>(
                R.id.markTv
        )

        courseTView.text = currentCourse?.name
        studentSurnameTView.text = currentStudent?.surname
        studentNameTView.text = currentStudent?.name
        markTView.text = marks?.value?.get(position)?.mark.toString()
    }

    override fun getItemCount(): Int = marks?.value?.size ?: 0
}