package com.example.teacherassistant.view.updateDatabase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.R
import com.example.teacherassistant.model.MarkEnum
import com.example.teacherassistant.viewModel.vm.MarkViewModel
import com.example.teacherassistant.viewModel.vm.StudentCourseViewModel
import kotlinx.android.synthetic.main.add_mark_layout.*
import java.text.SimpleDateFormat
import java.util.*

class AddMarkFragment : Fragment() {

    private lateinit var markViewModel: MarkViewModel
    private lateinit var studentCourseViewModel: StudentCourseViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?):
            View? {

        markViewModel = ViewModelProvider(requireActivity())
                .get(MarkViewModel::class.java)
        studentCourseViewModel = ViewModelProvider(requireActivity())
                .get(StudentCourseViewModel::class.java)

        return inflater.inflate(R.layout.add_mark_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            ArrayAdapter.createFromResource(
                    it,
                    R.array.marks_array,
                    android.R.layout.simple_spinner_item
            ).also {
                adapter -> adapter.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item)
                    markSpinner.adapter = adapter
            }
        }

        if(markViewModel.currentMark != null){

            markSpinner.setSelection(
                    resources.getStringArray(
                            R.array.marks_array).indexOf(
                            markViewModel.currentMark!!.mark.toString()
                    )
            )
            notePlainText.setText(markViewModel.currentMark!!.note)

            markBtn.setOnClickListener {
                markViewModel.editMark(
                        studentCourseViewModel.currentStudentCourse!!.id,
                        MarkEnum.valueOf(markSpinner.selectedItem.toString()),
                        notePlainText.text.toString(),
                        SimpleDateFormat("dd-MM-yyyy").format(Date())
                )
                activity?.onBackPressed()
            }
        }
        else{
            markBtn.setOnClickListener {
                markViewModel.addMark(
                        studentCourseViewModel.currentStudentCourse!!.id,
                        MarkEnum.valueOf(markSpinner.selectedItem.toString()),
                        notePlainText.text.toString(),
                        SimpleDateFormat("dd-MM-yyyy").format(Date())
                )
                activity?.onBackPressed()
            }
        }

    }

    companion object {
        fun newInstance() = AddMarkFragment()
    }
}