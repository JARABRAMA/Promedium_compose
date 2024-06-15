package com.jarabrama.promedium.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.jarabrama.promedium.model.Course
import com.jarabrama.promedium.service.CourseService
import com.jarabrama.promedium.utils.event.AddNewCourseEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class CourseViewModel(
    private val courseService: CourseService,
    private val navController: NavController
) : ViewModel() {
    private val eventBus: EventBus = EventBus.getDefault()

    init {
        eventBus.register(this)
    }

    private val _courses = MutableLiveData(courseService.findAll())
    val courses: LiveData<List<Course>> = _courses

    private val _average = MutableStateFlow(courseService.getAverage())
    val average: StateFlow<Double> = _average.asStateFlow()

    @Subscribe
    fun onCourseAdded(event: AddNewCourseEvent) {
        _courses.value = courseService.findAll()
    }

    fun onNewCourse() {
        navController.navigate("new-course")
    }
}