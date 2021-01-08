package com.example.teacherassistant.viewModel.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.model.entities.Student
import com.example.teacherassistant.model.projectDatabase
import com.example.teacherassistant.model.repositories.StudentRepo
import kotlinx.coroutines.launch

class StudentViewModel(application: Application): AndroidViewModel(application) {
    var currentStudent : Student? = null
    private val studentRepo: StudentRepo = StudentRepo((projectDatabase.getDatabase(application).studentDao()))
    val students: LiveData<List<Student>> = studentRepo.getAll

    fun insert(name: String, surname: String){
        viewModelScope.launch {
            studentRepo.insert(Student(id=0, name = name, surname = surname))
        }
    }

    fun delete(student: Student){
        viewModelScope.launch {
            studentRepo.delete(student)
        }
    }

    fun edit(name: String, surname: String){
        viewModelScope.launch {
            if(currentStudent != null)
                studentRepo.edit(Student(id=currentStudent!!.id, name = name, surname = surname))
        }
    }

    @JvmName("setCurrentStudent1")
    fun setCurrentStudent(student: Student?){
        currentStudent = student
    }

}