package com.example.teacherassistant.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="studentTable")
data class Student (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val surname: String
)