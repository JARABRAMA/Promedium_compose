package com.example.promedium.ui.theme.ui.view_model


import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.promedium.ui.theme.model.Course
import com.example.promedium.ui.theme.model.CourseProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NewCourseViewModel(private val navController: NavController): ViewModel() {
    private val _nameCourse = MutableStateFlow("")
    val nameCourse: StateFlow<String> = _nameCourse


    private val _creditsCourse = MutableStateFlow("")
    val creditCourse: StateFlow<String> = _creditsCourse
    fun onNameChange(name: String) {
        _nameCourse.value = name
    }
    fun onCreditsChange(credits: String){
        _creditsCourse.value = credits
    }

    fun clearValues(){
        _nameCourse.value = ""
        _creditsCourse.value =  ""
    }

    fun addNewCourse(): Boolean {
        return try {
            val newCourse = Course(
                name = _nameCourse.value,
                credits = _creditsCourse.value.toInt(),
                grades = mutableListOf()
            )
            CourseProvider.addCourse(newCourse)
            true
        } catch (_: NumberFormatException) {
            false
        }
    }

    fun navigateSemesterScreen(){
        navController.navigate("semester")
    }


}
