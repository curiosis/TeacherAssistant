package com.example.teacherassistant.model

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun toShortMark(value: String): MarkEnum = value?.let {
        MarkEnum.valueOf(value)
    }

    @TypeConverter
    fun fromShortMark(mark: MarkEnum?) : String = mark.toString()

    @TypeConverter
    fun toShortGender(value: String): GenderEnum = value?.let {
        GenderEnum.valueOf(value)
    }

    @TypeConverter
    fun fromShortGender(gender: GenderEnum?): String = gender.toString()
}