package com.example.promedium.ui.theme.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.promedium.ui.theme.model.Course
import com.example.promedium.ui.theme.model.CourseProvider
import kotlinx.coroutines.flow.MutableStateFlow

class NewGradeViewModel(private val navController: NavController, position: Int) : ViewModel() {
    private val courseProvider = MutableStateFlow(CourseProvider.Companion)

    val course: Course = courseProvider.value.getCourses()[position]


}