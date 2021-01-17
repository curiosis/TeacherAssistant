package com.example.teacherassistant.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.teacherassistant.model.Converters
import com.example.teacherassistant.model.GenderEnum

@Entity(tableName="studentTable")
@TypeConverters(Converters::class)
data class Student (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val surname: String,
    val gender: GenderEnum
)