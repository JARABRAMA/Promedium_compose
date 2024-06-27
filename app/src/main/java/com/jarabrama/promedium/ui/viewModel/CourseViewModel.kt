package com.jarabrama.promedium.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.jarabrama.promedium.model.Course
import com.jarabrama.promedium.service.CourseService
import com.jarabrama.promedium.utils.Utils
import com.jarabrama.promedium.utils.event.AddNewCourseEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class CourseViewModel(
    private val courseService: CourseService,
    private val navController: NavController
) : ViewModel() {
    private val eventBus: EventBus = EventBus.getDefault()

    private val _courses = MutableStateFlow(listOf<Course>())
    val courses = _courses.asStateFlow()

    private val _average = MutableStateFlow("")
    val average = _average.asStateFlow()

    private val _showOptionsDialog = MutableStateFlow(false);
    val showOptionsDialog = _showOptionsDialog.asStateFlow()

    private val _courseSelected = MutableStateFlow(Course(-1, "", 0));
    val courseSelected = _courseSelected.asStateFlow();

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

    fun onLongCourseClick(course: Course) {
        _showOptionsDialog.value = true
        _courseSelected.value = course
    }

    fun onEdit(courseId: Int) {
        // Todo()
    }

    fun onDelete(courseId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            courseService.delete(courseId)
            val courses = async { courseService.findAll() }
            withContext(Dispatchers.Main) {
                _courses.value = courses.await()
                _showOptionsDialog.value = false
            }
        }
    }

    fun onCloseDialog() {
        _showOptionsDialog.value = false
    }


}