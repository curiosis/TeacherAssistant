package com.example.teacherassistant.viewModel.vm

import android.app.Application
import androidx.lifecycle.*
import com.example.teacherassistant.model.MarkEnum
import com.example.teacherassistant.model.entities.Mark
import com.example.teacherassistant.model.entities.StudentCourse
import com.example.teacherassistant.model.projectDatabase
import com.example.teacherassistant.model.repositories.MarkRepo
import kotlinx.coroutines.launch

class MarkViewModel(application: Application): AndroidViewModel(application) {
    var currentMark :Mark? = null
    private val database = projectDatabase.getDatabase(application)
    private val markRepo : MarkRepo = MarkRepo(database.markDao())
    val marksByToday: LiveData<List<Mark>> = markRepo.getAllMarksByToday

    fun addMark(studentCourseId: Int,
                mark: MarkEnum,
                note: String,
                date: String
    ){
        viewModelScope.launch {
            markRepo.insert(Mark(
                id = 0, studentCourseId = studentCourseId, mark = mark,
                note = note, date = date))
        }
    }

    fun editMark(studentCourseId: Int,
                 mark: MarkEnum,
                 note: String,
                 date: String
    ){
        viewModelScope.launch {
            if(currentMark != null)
                markRepo.edit(Mark(
                    id = currentMark!!.id, studentCourseId = studentCourseId,
                    mark = mark, note = note, date = date
                ))
        }
    }

    fun deleteMark(mark: Mark){
        viewModelScope.launch {
            markRepo.delete(mark)
        }
    }

    @JvmName("setCurrentMark1")
    fun setCurrentMark(mark: Mark?){
        currentMark = mark
    }

    var marksByStudentCourse: LiveData<List<Mark>>? = null
    fun setMarksFromCurrentStudentCourse(studentCourse: StudentCourse?){
        if(studentCourse != null)
            marksByStudentCourse = markRepo.getAllMarksByCourseStudent((studentCourse.id))
    }
}