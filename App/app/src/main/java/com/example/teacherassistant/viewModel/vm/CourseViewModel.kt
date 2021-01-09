package com.example.teacherassistant.viewModel.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.model.entities.Course
import com.example.teacherassistant.model.entities.Student
import com.example.teacherassistant.model.projectDatabase
import com.example.teacherassistant.model.repositories.CourseRepo
import com.example.teacherassistant.model.repositories.StudentRepo
import kotlinx.coroutines.launch

class CourseViewModel(application: Application): AndroidViewModel(application) {
    var currentCourse : Course? = null
    private val courseRepo: CourseRepo = CourseRepo((projectDatabase.getDatabase(application).courseDao()))
    val courses: LiveData<List<Course>> = courseRepo.getAll

    fun insert(name: String){
        viewModelScope.launch {
            courseRepo.insert(Course(id=0, name = name))
        }
    }

    fun delete(course: Course){
        viewModelScope.launch {
            courseRepo.delete(course)
        }
    }

    fun edit(name: String){
        viewModelScope.launch {
            if(currentCourse != null)
                courseRepo.edit(Course(id=currentCourse!!.id, name = name))
        }
    }

    @JvmName("setCurrentCourse1")
    fun setCurrentCourse(course: Course?){
        currentCourse = course
    }

}