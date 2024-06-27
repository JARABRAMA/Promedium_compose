package com.jarabrama.promedium.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.jarabrama.promedium.model.Course
import com.jarabrama.promedium.service.CourseService
import com.jarabrama.promedium.utils.event.AddNewCourseEvent
import com.jarabrama.promedium.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class CourseViewModel(
    private val courseService: CourseService,
    private val navController: NavController
) : ViewModel() {
    private val eventBus: EventBus = EventBus.getDefault()

    private val _courses = MutableLiveData(listOf<Course>())
    val courses: LiveData<List<Course>> = _courses

    private val _average = MutableLiveData("")
    val average: LiveData<String> = _average

    init {
        eventBus.register(this)
        viewModelScope.launch(Dispatchers.IO) {
            val average = async { Utils.numberFormat(courseService.getAverage()) }
            val courses = async { courseService.findAll() }
            withContext(Dispatchers.Main) {
                _average.value = average.await()
                _courses.value = courses.await()
            }
        }
    }

    @Subscribe
    fun onCourseAdded(event: AddNewCourseEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            val courses = async { courseService.findAll(); }
            withContext(Dispatchers.Main) {
                _courses.value = courses.await();
            }
        }
    }

    fun onNewCourse() {
        navController.navigate("new-course")
    }

    fun onCourseClick(courseId: Int) {
        navController.navigate("grade/${courseId.toString()}")
    }
}