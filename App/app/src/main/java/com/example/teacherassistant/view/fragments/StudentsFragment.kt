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
import com.example.teacherassistant.viewModel.adapters.StudentListAdapter
import com.example.teacherassistant.viewModel.vm.StudentViewModel
import kotlinx.android.synthetic.main.students_fragment_layout.*

class StudentsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myLayoutManager: RecyclerView.LayoutManager

    private lateinit var myAdapter: StudentListAdapter
    private lateinit var viewModel: StudentViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?):
            View? {

        myLayoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity())
                .get(StudentViewModel::class.java)

        myAdapter = StudentListAdapter(
                viewModel.students,
                { student -> viewModel.delete(student)},
                { currStudent -> viewModel.setCurrentStudent(currStudent)})

        viewModel.students.observe(
                viewLifecycleOwner,
                {myAdapter.notifyDataSetChanged()})

        viewModel.setCurrentStudent(null)
        return inflater.inflate(R.layout.students_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = StudentsRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        AddStBtn.setOnClickListener {
            view.findNavController().navigate(
                    R.id.action_studentsFragment_to_addStudentFragment
            )
        }
    }

    companion object{
        fun newInstance() = StudentsFragment()
    }
}