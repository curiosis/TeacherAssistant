package com.example.teacherassistant.view.fragments

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
import com.example.teacherassistant.viewModel.adapters.MarkListAdapter
import com.example.teacherassistant.viewModel.vm.CourseViewModel
import com.example.teacherassistant.viewModel.vm.MarkViewModel
import com.example.teacherassistant.viewModel.vm.StudentCourseViewModel
import com.example.teacherassistant.viewModel.vm.StudentViewModel
import kotlinx.android.synthetic.main.mark_fragment_layout.*

class MarksFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myLayoutManager: RecyclerView.LayoutManager

    private lateinit var myAdapter: MarkListAdapter

    private lateinit var studentViewModel: StudentViewModel
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

        markViewModel.setMarksFromCurrentStudentCourse(
                studentCourseViewModel.currentStudentCourse
        )

        myAdapter = MarkListAdapter(
                markViewModel.marksByStudentCourse!!,{
                 mark -> markViewModel.deleteMark(mark)
                },{
                    mark -> markViewModel.setCurrentMark(mark)
                })

        markViewModel.marksByStudentCourse!!.observe(
                viewLifecycleOwner,{
                    myAdapter.notifyDataSetChanged()
                })

        return inflater.inflate(R.layout.mark_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = markRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        markStudentSurnameTV.text = studentViewModel.currentStudent!!.surname
        markStudentNameTV.text = studentViewModel.currentStudent!!.name
        markCourseNameTV.text = courseViewModel.currentCourse!!.name

        addMarkBtn.setOnClickListener {
            view.findNavController().navigate(
                    R.id.action_marksFragment_to_addMarkFragment
            )
        }
    }

    companion object {
        fun newInstance() = MarksFragment()
    }
}