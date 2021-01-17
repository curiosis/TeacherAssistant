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
import com.example.teacherassistant.viewModel.adapters.StudentsCourseAdapter
import com.example.teacherassistant.viewModel.vm.CourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentCourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentViewModel
import kotlinx.android.synthetic.main.students_course_fragment_layout.*

class StudentsCourseFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myLayoutManager : RecyclerView.LayoutManager

    private lateinit var myAdapter : StudentsCourseAdapter

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

        studentCourseViewModel.setStudentsFCC(courseViewModel.currentCourse)

        myAdapter = StudentsCourseAdapter(
                studentCourseViewModel.studentsFromCurrentCourse!!,
                {
                    student -> studentCourseViewModel.deleteStudentCourse(student, courseViewModel.currentCourse!!)
                },
                {
                    student -> studentViewModel.setCurrentStudent(student)
                    studentCourseViewModel.setCurrentStudentCourse(student, courseViewModel.currentCourse)
                })

        studentCourseViewModel.studentsFromCurrentCourse!!.observe(
                viewLifecycleOwner,{
                    myAdapter.notifyDataSetChanged()
        })

        studentCourseViewModel.con.observe(
                viewLifecycleOwner,{
                    myAdapter.notifyDataSetChanged()
        })

        studentViewModel.setCurrentStudent(null)

        return inflater.inflate(R.layout.students_course_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = StsCrsRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        currCrsNameStTV.text = courseViewModel.currentCourse!!.name

        AddStsCrsBtn.setOnClickListener {
            view.findNavController().navigate(
                    R.id.action_studentsCourseFragment_to_addSCFragment
            )
        }
    }

    companion object{
        fun newInstance() = StudentsCourseFragment()
    }
}