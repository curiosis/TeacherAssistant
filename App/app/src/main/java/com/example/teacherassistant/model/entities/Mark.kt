package com.example.teacherassistant.model.entities

import androidx.room.*
import com.example.teacherassistant.model.Converters
import com.example.teacherassistant.model.MarkEnum

@Entity(tableName = "markTable", foreignKeys = [
    ForeignKey(
        entity = StudentCourse::class,
        parentColumns = ["id"],
        childColumns = ["studentCourseId"],
        onDelete = ForeignKey.CASCADE)
])
@TypeConverters(Converters::class)
data class Mark(
    @PrimaryKey(autoGenerate = true)
        val id: Int,
    val studentCourseId: Int,
    val mark: MarkEnum,
    val note: String,
    val date: String
)