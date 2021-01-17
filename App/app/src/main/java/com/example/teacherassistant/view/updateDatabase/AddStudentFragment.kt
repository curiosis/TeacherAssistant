package com.example.teacherassistant.view.updateDatabase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.R
import com.example.teacherassistant.model.GenderEnum
import com.example.teacherassistant.viewModel.vm.StudentViewModel
import kotlinx.android.synthetic.main.add_student_layout.*

class AddStudentFragment: Fragment() {

    private lateinit var viewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity())
                .get(StudentViewModel::class.java)

        return inflater.inflate(R.layout.add_student_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let{
            ArrayAdapter.createFromResource(
                it,
                R.array.students_gender_array,
                android.R.layout.simple_spinner_item
            ).also {
                adapter -> adapter.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item)
                    genderSpinner.adapter = adapter
            }
        }

        if(viewModel.currentStudent != null){

            genderSpinner.setSelection(
                resources.getStringArray(
                    R.array.students_gender_array).indexOf(
                    viewModel.currentStudent!!.gender.toString()
                    )
            )

            nameInput.setText(viewModel.currentStudent!!.name)
            surnameInput.setText(viewModel.currentStudent!!.surname)

            addBtn.setOnClickListener{
                if(!nameInput.text.isNullOrEmpty() && !surnameInput.text.isNullOrEmpty())
                    viewModel.edit(nameInput.text.toString(),
                        surnameInput.text.toString(),
                        GenderEnum.valueOf(genderSpinner.selectedItem.toString()))
                activity?.onBackPressed()
            }
        }

        else{
            addBtn.setOnClickListener{
                if(!nameInput.text.isNullOrEmpty() && !surnameInput.text.isNullOrEmpty())
                    viewModel.insert(
                            nameInput.text.toString(),
                            surnameInput.text.toString(),
                            GenderEnum.valueOf(genderSpinner.selectedItem.toString()))

                activity?.onBackPressed()
            }
        }
    }

    companion object{
        fun newInstance() = AddStudentFragment()
    }
}