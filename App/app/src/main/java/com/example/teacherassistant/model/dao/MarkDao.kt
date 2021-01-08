package com.example.teacherassistant.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.model.entities.Mark

@Dao
interface MarkDao {
    @Insert
    suspend fun insertMark(mark: Mark)

    @Delete
    suspend fun deleteMark(mark: Mark)

    @Update
    suspend fun editMark(mark: Mark)

    @Query("SELECT * FROM markTable WHERE date == :date")
    fun getAllMarksByDay(date: String): LiveData<List<Mark>>

    @Query("SELECT * FROM markTable WHERE studentCourseId == :studentCourseId")
    fun getAllMarksByStudentCourse(studentCourseId: Int): LiveData<List<Mark>>
}