package com.example.teacherassistant.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.teacherassistant.R
import kotlinx.android.synthetic.main.main_menu_layout.*

class MainMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_menu_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentsBtn.setOnClickListener{
            view.findNavController().navigate(
                R.id.action_mainMenuFragment_to_studentsFragment
            )
        }

        coursesBtn.setOnClickListener{
            view.findNavController().navigate(
                R.id.action_mainMenuFragment_to_coursesFragment
            )
        }

        marksBtn.setOnClickListener{
            view.findNavController().navigate(
                R.id.action_mainMenuFragment_to_reportFragment
            )
        }
    }

    companion object {
        fun newInstance() = MainMenuFragment()
    }

}