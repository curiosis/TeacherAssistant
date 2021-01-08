package com.example.teacherassistant.model.entities

import androidx.room.*

@Entity(tableName = "markTable", foreignKeys = [
    ForeignKey(
        entity = StudentCourse::class,
        parentColumns = ["id"],
        childColumns = ["studentCourseId"],
        onDelete = ForeignKey.CASCADE)
])
//@TypeConverter(Converters::class)
data class Mark(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val studentCourseId: Int,
    val mark: Float,
    val note: String,
    val date: String
)