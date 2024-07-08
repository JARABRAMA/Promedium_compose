package com.jarabrama.promedium.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.jarabrama.promedium.model.Course
import com.jarabrama.promedium.service.CourseService
import com.jarabrama.promedium.utils.event.AddNewCourseEvent
import com.jarabrama.promedium.utils.event.InvalidInputEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import java.lang.Integer.parseInt

class EditCourseViewModel(
    private val courseService: CourseService,
    private val navController: NavController,
    private val courseId: Int
) : ViewModel() {
    private val eventBus = EventBus.getDefault()

    private lateinit var course: Course

    private val _name = MutableStateFlow(course.name)
    val name = _name.asStateFlow()

    private val _credits = MutableStateFlow(course.credits.toString())
    val credits = _credits.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val courseDeferred = async { courseService.get(courseId) }
            withContext(Dispatchers.Main) {
                course = courseDeferred.await()
            }
        }
    }

    fun onSave() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                course.name = _name.value
                course.credits = parseInt(_credits.value)
                courseService.update(course)
            } catch (e: NumberFormatException) {
                eventBus.post(InvalidInputEvent("Credit value must be an integer number"))
                Log.e("EditingCourseViewModel", e.message, e)
            }
        }
        eventBus.post(AddNewCourseEvent(name = course.name, credits = course.credits))
        navController.popBackStack()
    }

    fun onNameChange(name: String) {
        _name.value = name
    }

    fun onCreditsChange(creditsValue: String) {
        _credits.value = creditsValue
    }
}