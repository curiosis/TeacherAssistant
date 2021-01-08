package com.example.teacherassistant.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.teacherassistant.model.entities.Course
import com.example.teacherassistant.model.entities.Student
import com.example.teacherassistant.model.entities.StudentCourse

@Dao
interface StudentCourseDao {
    @Insert
    suspend fun insertStudentCourse(studentCourse: StudentCourse)

    @Query("DELETE FROM studentCourseTable WHERE courseId == :courseId AND studentId == :studentId")
    suspend fun deleteStudentCourse(courseId: Int, studentId: Int)

    @Query("SELECT * FROM studentTable WHERE id IN (SELECT studentId FROM studentCourseTable WHERE courseId == :courseId) ORDER BY surname, name")
    fun getAllStudentsFromCourse(courseId: Int): LiveData<List<Student>>

    @Query("SELECT * FROM studentTable WHERE id NOT IN (SELECT studentId FROM studentCourseTable WHERE courseId == :courseId) ORDER BY surname, name")
    fun getAllStudentsOutOfCourse(courseId: Int): LiveData<List<Student>>

    @Query("SELECT * FROM courseTable WHERE id IN (SELECT courseId FROM studentCourseTable WHERE studentId == :studentId)")
    fun getAllCoursesFromStudent(studentId: Int): LiveData<List<Course>>

    @Query("SELECT * FROM courseTable WHERE id NOT IN (SELECT courseId FROM studentCourseTable WHERE studentId == :studentId)")
    fun getAllCoursesOutOfStudent(studentId: Int): LiveData<List<Course>>

    @Query("SELECT * FROM studentCourseTable")
    fun getAllStudentCourse(): LiveData<List<StudentCourse>>
}