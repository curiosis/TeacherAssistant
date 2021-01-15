package com.example.teacherassistant.viewModel.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.model.entities.Course
import com.example.teacherassistant.model.entities.Student
import com.example.teacherassistant.model.entities.StudentCourse
import com.example.teacherassistant.model.projectDatabase
import com.example.teacherassistant.model.repositories.StudentCourseRepo
import kotlinx.coroutines.launch


class StudentCourseViewModel(application: Application):AndroidViewModel(application) {

    private val database = projectDatabase.getDatabase(application)
    private val studentCourseRepo : StudentCourseRepo = StudentCourseRepo(database.studentCourseDao())

    fun addStudentCourse(student : Student, course : Course){
        viewModelScope.launch {
            studentCourseRepo.add(
                StudentCourse(
                    id = 0, studentId = student.id, courseId = course.id
                )
            )
        }
    }

    fun deleteStudentCourse(student: Student, course: Course){
        viewModelScope.launch {
            studentCourseRepo.delete(
                student.id, course.id
            )
        }
    }

    var studentsFromCurrentCourse : LiveData<List<Student>>? = null
    var studentsNotFromCurrentCourse : LiveData<List<Student>>? = null

    fun setStudentsFCC(course: Course?){
        if(course != null){
            studentsFromCurrentCourse = studentCourseRepo.getStudentsFromCourse((course.id))
        }
    }

    fun setStudentsNFCC(course: Course?){
        if(course != null){
            studentsNotFromCurrentCourse = studentCourseRepo.getStudentsOutOfCourse(course.id)
        }
    }

    var coursesFromCurrentStudent : LiveData<List<Course>>? = null
    var coursesNotFromCurrentStudent : LiveData<List<Course>>? = null

    fun setCoursesFCS(student: Student?){
        if(student != null){
            coursesFromCurrentStudent = studentCourseRepo.getCoursesFromStudent((student.id))
        }
    }

    fun setCoursesNFCS(student: Student?){
        if(student != null){
            coursesNotFromCurrentStudent = studentCourseRepo.getCoursesOutOfStudent(student.id)
        }
    }

    var currentStudentCourse : StudentCourse? = null
    var con : LiveData<List<StudentCourse>> = studentCourseRepo.getAll

    @JvmName("setCurrentStudentCourse1")
    fun setCurrentStudentCourse(student: Student?, course: Course?){
        if(student != null && course != null){
            currentStudentCourse = con.value?.find {
                x -> x.studentId == student.id && x.courseId == course.id
            }
        }
    }
}