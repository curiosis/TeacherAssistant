package com.example.teacherassistant.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.model.entities.Course

@Dao
interface CourseDao {
    @Insert
    suspend fun insertCourse(course: Course)

    @Delete
    suspend fun deleteCourse(course: Course)

    @Update
    suspend fun editCourse(course: Course)

    @Query("SELECT * FROM courseTable")
    fun getAllCourses(): LiveData<List<Course>>
}