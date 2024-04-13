package com.example.promedium.ui.theme.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.promedium.ui.theme.model.Course
import com.example.promedium.ui.theme.model.CourseProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CourseViewModel(private val navController: NavController, private val positionCourse: Int) :
    ViewModel() {
    fun onNewGrade(positionCourse: Int) {
        navController.navigate("new_grade/${positionCourse}")
    }

    private val _courseProvider = CourseProvider.Companion

    private val _course = MutableStateFlow(_courseProvider.getCourses()[positionCourse])
    val course: StateFlow<Course> = _course

    fun getPosition(): Int {
        return positionCourse
    }
}