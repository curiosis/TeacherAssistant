package com.example.teacherassistant.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "studentCourseTable", foreignKeys = [
    ForeignKey(
        entity = Student::class,
        parentColumns = ["id"],
        childColumns = ["studentId"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Course::class,
        parentColumns = ["id"],
        childColumns = ["courseId"],
        onDelete = ForeignKey.CASCADE
    )
])
data class StudentCourse (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val studentId: Int,
    val courseId: Int
    )