package com.example.teacherassistant.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.model.entities.Student

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Update
    suspend fun editStudent(student: Student)

    @Query("SELECT * FROM studentTable ORDER BY surname, name")
    fun getAllStudents(): LiveData<List<Student>>
}