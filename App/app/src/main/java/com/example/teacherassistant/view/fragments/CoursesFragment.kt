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
import com.example.teacherassistant.viewModel.adapters.CourseListAdapter
import com.example.teacherassistant.viewModel.vm.CourseViewModel
import kotlinx.android.synthetic.main.courses_fragment_layout.*

class CoursesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var myLayoutManager: RecyclerView.LayoutManager

    private lateinit var myAdapter: CourseListAdapter
    private lateinit var viewModel: CourseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myLayoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)

        myAdapter = CourseListAdapter(
                viewModel.courses,
                { course -> viewModel.delete(course)},
                { currCourse -> viewModel.setCurrentCourse(currCourse)})

        viewModel.courses.observe(
                viewLifecycleOwner,
                {myAdapter.notifyDataSetChanged()})

        viewModel.setCurrentCourse(null)
        return inflater.inflate(R.layout.courses_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = CoursesRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        AddCrsBtn.setOnClickListener {
            view.findNavController().navigate(
                    R.id.action_coursesFragment_to_addCourseFragment
            )
        }
    }

    companion object{
        fun newInstance() = CoursesFragment()
    }
}
