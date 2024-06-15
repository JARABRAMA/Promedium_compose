package com.jarabrama.promedium.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.jarabrama.promedium.service.CourseService
import com.jarabrama.promedium.utils.event.AddNewCourseEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.Integer.parseInt
import org.greenrobot.eventbus.*

class NewCourseViewModel(
    private val navController: NavController,
    private val courseService: CourseService
) : ViewModel() {
    private val eventBus = EventBus.getDefault()

    private val _courseName = MutableStateFlow("");
    val courseName: StateFlow<String> = _courseName.asStateFlow()

    private val _courseCredits = MutableStateFlow("")
    val courseCredits: StateFlow<String> = _courseCredits.asStateFlow()

    private var _courseAdded = MutableStateFlow(false)
    var courseAdded: StateFlow<Boolean> = _courseAdded.asStateFlow()
    fun onCreditsChange(credits: String) {
        _courseCredits.value = credits
    }

    fun onNameChange(name: String) {
        _courseName.value = name
    }

    private fun goBack() {
        navController.navigate("courses")
    }

    fun onAddCourse() {
        val name = _courseName.value
        val credits = parseInt(_courseCredits.value)
        try {
            _courseName.value = ""
            _courseCredits.value = ""
            courseService.newCourse(name, credits)
            goBack()
            eventBus.post(AddNewCourseEvent(name, credits))
        } catch (e: NumberFormatException) {
            e.message ?: let { Log.e("ERROR", "on add course ${e.message}") }
        }
    }
}