package com.jarabrama.promedium.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.jarabrama.promedium.service.CourseService
import kotlinx.coroutines.flow.MutableStateFlow

class CourseViewModel(private val courseService: CourseService, val navController: NavController): ViewModel() {

    private val _courses = MutableStateFlow(courseService.findAll())
    val courses = _courses.value

    private val _average = MutableStateFlow(courseService.getAverage())
    val average = _average.value

    fun onNewCourse(){
        navController.navigate("new-course")
    }
}