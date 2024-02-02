package com.example.promedium.ui.theme.ui.view_model

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.promedium.ui.theme.model.Course
import com.example.promedium.ui.theme.model.CourseProvider
import kotlinx.coroutines.flow.MutableStateFlow

class SemesterViewModel(private val navController: NavController) : ViewModel() {

    private val _coursesProvider = MutableStateFlow(CourseProvider)

    val courses = _coursesProvider.value.getCourses()

    fun navigateNewCourseScreen() {
        navController.navigate("new_course")
    }

    fun getCreditAverage(): String {
        var totalCredits = 0

        courses.map { course ->
            totalCredits += course.credits!!
        }
        var creditAverage = 0.0
        courses.map { course ->
            creditAverage += course.credits!! * course.average()
        }
        return if (totalCredits == 0){
            "0"
        } else{
            (creditAverage / totalCredits).toString()
        }
    }

    fun onClickCourse(position: Int) {
        val positionCourse = position.toString()
        navController.navigate("course/${positionCourse}")
    }


}