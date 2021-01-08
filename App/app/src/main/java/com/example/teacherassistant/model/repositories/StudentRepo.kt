package com.example.teacherassistant.model.repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.model.dao.StudentDao
import com.example.teacherassistant.model.entities.Student


class StudentRepo (private val studentDao: StudentDao){

    val getAll: LiveData<List<Student>> = studentDao.getAllStudents()

    suspend fun insert(student: Student) = studentDao.insertStudent(student)
    suspend fun delete(student: Student) = studentDao.deleteStudent(student)
    suspend fun edit(student: Student) = studentDao.editStudent(student)
}