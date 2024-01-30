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

    private val _creditsCourse = MutableStateFlow<Int?>(null)

    fun onNameChange(name: String) {
        _nameCourse.value = name
    }
    fun onCreditsChange(credits: String){
        println("the number is $credits")
        try {
            _creditsCourse.value = credits.toInt()
        } catch(_: NumberFormatException){
            println("error de formato de numero")
            println("no se pudo converter $credits a entero")
            _creditsCourse.value = 1
        }
    }
    fun creditValue(): String {
        return if (_creditsCourse.value == null){
            ""
        } else {
            _creditsCourse.value.toString()
        }
    }
    fun clearValues(){
        _nameCourse.value = ""
        _creditsCourse.value =  null
    }

    fun addNewCourse(): Boolean {
        return try {
            val newCourse = Course(
                name = _nameCourse.value,
                credits = _creditsCourse.value,
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
