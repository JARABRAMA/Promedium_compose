package com.example.promedium.ui.theme.model

import androidx.compose.runtime.Composable

class CourseProvider {


    companion object {
        // creacion de una lista mutable que guarde los valores de nuestro object
        private val courses: MutableList<Course> = mutableListOf(
            Course("Math", 3, mutableListOf(
                Grade(
                    "first Parcial",
                    4.8,
                    25.0
                )
            ))
        )

        fun  getCourses(): MutableList<Course> = courses
        fun addCourse(course: Course) {courses.add(course)}
    }
}