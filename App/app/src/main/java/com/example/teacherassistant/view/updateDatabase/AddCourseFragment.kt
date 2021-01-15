package com.example.teacherassistant.view.updateDatabase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.R
import com.example.teacherassistant.viewModel.vm.CourseViewModel
import kotlinx.android.synthetic.main.add_course_layout.*

class AddCourseFragment : Fragment() {

    private lateinit var viewModel : CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity())
                .get(CourseViewModel::class.java)

        return inflater.inflate(R.layout.add_course_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(viewModel.currentCourse != null){
            courseInput.setText(viewModel.currentCourse!!.name)
            courseBtn.setOnClickListener{
                if(!courseInput.text.isNullOrEmpty())
                    viewModel.edit(courseInput.text.toString())
                activity?.onBackPressed()
            }
        }

        else{
            courseBtn.setOnClickListener {
                if (!courseInput.text.isNullOrEmpty())
                    viewModel.insert(courseInput.text.toString())
                activity?.onBackPressed()
            }
        }
    }

    companion object{
        fun newInstance() = AddCourseFragment()
    }
}