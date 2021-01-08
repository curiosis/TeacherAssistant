package com.example.teacherassistant.model.repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.model.dao.CourseDao
import com.example.teacherassistant.model.entities.Course
import com.example.teacherassistant.model.entities.Student


class CourseRepo(private val courseDao: CourseDao) {

    val getAll: LiveData<List<Course>> = courseDao.getAllCourses()

    suspend fun insert(course: Course) = courseDao.insertCourse(course)
    suspend fun delete(course: Course) = courseDao.deleteCourse(course)
    suspend fun edit(course: Course) = courseDao.editCourse(course)
}