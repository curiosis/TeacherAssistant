package com.example.teacherassistant.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teacherassistant.model.dao.CourseDao
import com.example.teacherassistant.model.dao.MarkDao
import com.example.teacherassistant.model.dao.StudentCourseDao
import com.example.teacherassistant.model.dao.StudentDao
import com.example.teacherassistant.model.entities.Course
import com.example.teacherassistant.model.entities.Mark
import com.example.teacherassistant.model.entities.Student
import com.example.teacherassistant.model.entities.StudentCourse

@Database(
    entities = [
        Student::class,
        Mark::class,
        Course::class,
        StudentCourse::class
        ],
    version = 3,
    exportSchema = false)


abstract class projectDatabase: RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun markDao(): MarkDao
    abstract fun courseDao(): CourseDao
    abstract fun studentCourseDao(): StudentCourseDao

    companion object{
        @Volatile
        private var INSTANCE: projectDatabase? = null
        private val NAME = "projectDatabase"

        fun getDatabase(context: Context): projectDatabase{
            val tempIstance = INSTANCE

            if(tempIstance != null)
                return tempIstance
            else
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        projectDatabase::class.java,
                        NAME
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                    return instance
                }
        }
    }

}