package com.example.teacherassistant.model.repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.model.dao.StudentCourseDao
import com.example.teacherassistant.model.entities.StudentCourse


class StudentCourseRepo(private val studentCourseDao: StudentCourseDao) {

    val getAll: LiveData<List<StudentCourse>> = studentCourseDao.getAllStudentCourse()

    fun getStudentsFromCourse(courseId : Int) = studentCourseDao.getAllStudentsFromCourse(courseId)
    fun getStudentsOutOfCourse(courseId : Int) = studentCourseDao.getAllStudentsOutOfCourse(courseId)
    fun getCoursesFromStudent(studentId : Int) = studentCourseDao.getAllCoursesFromStudent(studentId)
    fun getCoursesOutOfStudent(studentId : Int) = studentCourseDao.getAllCoursesOutOfStudent(studentId)

    suspend fun add(studentCourse : StudentCourse) = studentCourseDao.insertStudentCourse(studentCourse)
    suspend fun delete(courseId : Int, studentID : Int) = studentCourseDao.deleteStudentCourse(courseId, studentID)
}