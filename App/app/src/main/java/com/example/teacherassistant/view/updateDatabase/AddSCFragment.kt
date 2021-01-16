package com.example.teacherassistant.view.updateDatabase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.adapters.StudentsNInCAdapter
import com.example.teacherassistant.viewModel.vm.CourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentCourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentViewModel
import kotlinx.android.synthetic.main.students_not_in_course_layout.*

class AddSCFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myLayoutManager: RecyclerView.LayoutManager

    private lateinit var myAdapter: StudentsNInCAdapter

    private lateinit var studentViewModel: StudentViewModel
    private lateinit var courseViewModel: CourseViewModel
    private lateinit var studentCourseViewModel: StudentCourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myLayoutManager = LinearLayoutManager(context)

        studentViewModel = ViewModelProvider(requireActivity())
            .get(StudentViewModel::class.java)
        courseViewModel = ViewModelProvider(requireActivity())
            .get(CourseViewModel::class.java)
        studentCourseViewModel = ViewModelProvider(requireActivity())
            .get(StudentCourseViewModel::class.java)

        studentCourseViewModel.setStudentsNFCC(courseViewModel.currentCourse)

        myAdapter = StudentsNInCAdapter(
            studentCourseViewModel.studentsNotFromCurrentCourse!!
        ) { student ->
            studentCourseViewModel.addStudentCourse(student, courseViewModel.currentCourse!!)
        }

        studentCourseViewModel.studentsNotFromCurrentCourse!!.observe(
            viewLifecycleOwner,{
                myAdapter.notifyDataSetChanged()
            }
        )

        return inflater.inflate(R.layout.students_not_in_course_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = studentsNInCRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }

    companion object {
        fun newInstance() = AddSCFragment()
    }
}