package com.jarabrama.promedium.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.jarabrama.promedium.service.CourseService
import com.jarabrama.promedium.utils.event.AddNewCourseEvent
import com.jarabrama.promedium.utils.event.InvalidInputEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import java.lang.Integer.parseInt

class NewCourseViewModel(
    private val navController: NavController,
    private val courseService: CourseService
) : ViewModel() {
    private val eventBus = EventBus.getDefault()

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

    private fun goBack() {
        navController.navigate("courses")
    }

    fun onAddCourse() {
        try {
            val name = _courseName.value
            val credits = parseInt(_courseCredits.value)
            _courseName.value = ""
            _courseCredits.value = ""
            courseService.newCourse(name, credits)
            eventBus.post(AddNewCourseEvent(name, credits))
            goBack()
        } catch (e: NumberFormatException) {
            eventBus.post(InvalidInputEvent("Credit value must be an integer number"))
            e.message ?: let { Log.e("ERROR", "on add course ${e.message}") }
        }
    }

    fun onBack() {
        navController.popBackStack()
    }
}