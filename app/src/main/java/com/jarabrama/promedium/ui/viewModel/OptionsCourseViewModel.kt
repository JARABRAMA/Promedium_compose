package com.jarabrama.promedium.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.jarabrama.promedium.exceptions.courseExceptions.CourseNotFoundException
import com.jarabrama.promedium.service.CourseService
import com.jarabrama.promedium.utils.event.InvalidInputEvent
import org.greenrobot.eventbus.EventBus

class OptionsCourseViewModel(
    private val navController: NavController,
    private val courseId: Int,
    private val courseService: CourseService
) : ViewModel() {

    private val eventBus = EventBus.getDefault()
    fun onEdit() {

    }

    fun onDelete() {
        try {
            courseService.delete(courseId)
            navController.navigate("courses")
        } catch (e: CourseNotFoundException) {
           e.message?.let { eventBus.post(InvalidInputEvent(it)) }
        }
    }

    fun onBack() {
        navController.popBackStack()
    }
}