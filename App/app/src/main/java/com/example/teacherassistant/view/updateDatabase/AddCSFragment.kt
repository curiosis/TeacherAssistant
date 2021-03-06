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
import com.example.teacherassistant.viewModel.adapters.CoursesNInSAdapter
import com.example.teacherassistant.viewModel.vm.CourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentCourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentViewModel
import kotlinx.android.synthetic.main.courses_not_in_student_layout.*

class AddCSFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myLayoutManager: RecyclerView.LayoutManager

    private lateinit var myAdapter: CoursesNInSAdapter

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

        studentCourseViewModel.setCoursesNFCS(studentViewModel.currentStudent)

        myAdapter = CoursesNInSAdapter(
            studentCourseViewModel.coursesNotFromCurrentStudent!!){
            course -> studentCourseViewModel.addStudentCourse(studentViewModel.currentStudent!!, course)
        }

        studentCourseViewModel.coursesNotFromCurrentStudent!!.observe(
            viewLifecycleOwner,{
                myAdapter.notifyDataSetChanged()
            }
        )

        return inflater.inflate(R.layout.courses_not_in_student_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = coursesNInSRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }

    companion object{
        fun newInstance() = AddCSFragment()
    }
}