package com.example.teacherassistant.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courseTable")
data class Course (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
    )