package com.example.teacherassistant.view.conn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.adapters.CourseStudentAdapter
import com.example.teacherassistant.viewModel.adapters.StudentsCourseAdapter
import com.example.teacherassistant.viewModel.vm.CourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentCourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentViewModel
import kotlinx.android.synthetic.main.courses_fragment_layout.*
import kotlinx.android.synthetic.main.students_course_fragment_layout.*

class CourseStudentFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myLayoutManager : RecyclerView.LayoutManager

    private lateinit var myAdapter : CourseStudentAdapter

    private lateinit var studentViewModel : StudentViewModel
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

        studentCourseViewModel.setCoursesFCS(studentViewModel.currentStudent)

        myAdapter = CourseStudentAdapter(
                studentCourseViewModel.coursesFromCurrentStudent!!,
                {
                    course -> studentCourseViewModel.deleteStudentCourse(studentViewModel.currentStudent!!, course)
                },
                {
                    course -> courseViewModel.setCurrentCourse(course)
                    studentCourseViewModel.setCurrentStudentCourse(studentViewModel.currentStudent, course)
                })

        studentCourseViewModel.coursesFromCurrentStudent!!.observe(
                viewLifecycleOwner,{
                    myAdapter.notifyDataSetChanged()
        })

        studentCourseViewModel.con.observe(
                viewLifecycleOwner,{
            myAdapter.notifyDataSetChanged()
        })

        courseViewModel.setCurrentCourse(null)

        return inflater.inflate(R.layout.courses_student_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = StsCrsRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        AddCrsBtn.setOnClickListener {
            view.findNavController().navigate(
                    R.id.action_courseStudentFragment_to_addCSFragment
            )
        }
    }

    companion object{
        fun newInstance() = CourseStudentFragment()
    }
}