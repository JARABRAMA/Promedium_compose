package com.jarabrama.promedium.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.jarabrama.promedium.service.CourseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.Integer.parseInt

class NewCourseViewModel(
    private val navController: NavController,
    private val courseService: CourseService
) : ViewModel() {
    private val _courseName = MutableStateFlow("");
    val courseName: StateFlow<String> = _courseName.asStateFlow()

    private val _courseCredits = MutableStateFlow("")
    val courseCredits: StateFlow<String> = _courseCredits.asStateFlow()

    fun onCreditsChange(credits: String) {
        _courseCredits.value = credits
    }

    fun onNameChange(name: String) {
        _courseName.value = name
    }

    fun goBack() {
        navController.navigate("courses")
    }

    fun onAddCourse() {
        courseService.newCourse(name = _courseName.value, credits = parseInt(_courseCredits.value))
        _courseCredits.value = ""
        _courseCredits.value = ""
    }
}