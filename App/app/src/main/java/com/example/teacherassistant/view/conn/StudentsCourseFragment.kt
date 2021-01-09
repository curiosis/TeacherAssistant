package com.example.teacherassistant.view.conn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.viewModel.adapters.StudentsCourseAdapter
import com.example.teacherassistant.viewModel.vm.CourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentCourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentViewModel

class StudentsCourseFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter : StudentsCourseAdapter
    lateinit var studentViewModel : StudentViewModel
    lateinit var courseViewModel: CourseViewModel
    lateinit var studentCourseViewModel: StudentCourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutManager = LinearLayoutManager(context)
        studentViewModel = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        courseViewModel = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
        studentCourseViewModel = ViewModelProvider(requireActivity()).get(StudentCourseViewModel::class.java)
        studentCourseViewModel.setStudentsFCC(courseViewModel.currentCourse)

        adapter = StudentsCourseAdapter()
    }
}