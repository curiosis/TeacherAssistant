package com.example.teacherassistant.model.repositories

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.example.teacherassistant.model.dao.MarkDao
import com.example.teacherassistant.model.entities.Course
import com.example.teacherassistant.model.entities.Mark
import com.example.teacherassistant.model.entities.StudentCourse
import java.text.SimpleDateFormat
import java.util.*

class MarkRepo(private val markDao: MarkDao) {
    @SuppressLint("SimpleDateFormat")
    val getAllMarksByToday: LiveData<List<Mark>> = markDao.getAllMarksByDay(SimpleDateFormat("dd-MM-yyyy").format(Date()))

    fun getAllMarksByCourseStudent(studentCourseId: Int) = markDao.getAllMarksByStudentCourse(studentCourseId)

    suspend fun insert(mark: Mark) = markDao.insertMark(mark)
    suspend fun delete(mark: Mark) = markDao.deleteMark(mark)
    suspend fun edit(mark: Mark) = markDao.editMark(mark)


}