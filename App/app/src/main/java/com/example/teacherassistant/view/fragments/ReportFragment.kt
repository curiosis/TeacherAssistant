package com.example.teacherassistant.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.adapters.ReportAdapter
import com.example.teacherassistant.viewModel.vm.CourseViewModel
import com.example.teacherassistant.viewModel.vm.MarkViewModel
import com.example.teacherassistant.viewModel.vm.StudentCourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentViewModel
import kotlinx.android.synthetic.main.report_fragment_layout.*
import java.text.SimpleDateFormat
import java.util.*

class ReportFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myLayoutManager : RecyclerView.LayoutManager

    private lateinit var myAdapter : ReportAdapter

    private lateinit var studentViewModel : StudentViewModel
    private lateinit var courseViewModel: CourseViewModel
    private lateinit var studentCourseViewModel: StudentCourseViewModel
    private lateinit var markViewModel: MarkViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?):
            View? {

        myLayoutManager = LinearLayoutManager(context)

        studentViewModel = ViewModelProvider(requireActivity())
                .get(StudentViewModel::class.java)
        courseViewModel = ViewModelProvider(requireActivity())
                .get(CourseViewModel::class.java)
        studentCourseViewModel = ViewModelProvider(requireActivity())
                .get(StudentCourseViewModel::class.java)
        markViewModel = ViewModelProvider(requireActivity())
                .get((MarkViewModel::class.java))

        studentViewModel.students.observe(
                viewLifecycleOwner
        ){

        }

        courseViewModel.courses.observe(
                viewLifecycleOwner
        ){

        }

        studentCourseViewModel.con.observe(
                viewLifecycleOwner
        ){

        }

        myAdapter = ReportAdapter(markViewModel.marksByToday, studentCourseViewModel, studentViewModel, courseViewModel)

        markViewModel.marksByToday.observe(
                viewLifecycleOwner,{
                myAdapter.notifyDataSetChanged()
                }
        )

        return inflater.inflate(R.layout.report_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = reportRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        reportTView.text = "Report of the day: " + SimpleDateFormat("dd-MM-yyyy").format(Date())
    }

    companion object {
        fun newInstance() = ReportFragment()
    }

}