package com.jarabrama.promedium.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.jarabrama.promedium.model.Grade
import com.jarabrama.promedium.service.CourseService
import com.jarabrama.promedium.service.GradeService
import com.jarabrama.promedium.utils.Utils
import com.jarabrama.promedium.utils.event.AddNewGradeEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class GradeViewModel(
    private val navController: NavController,
    private val gradeService: GradeService,
    private val courseService: CourseService,
    private val courseId: Int
) : ViewModel() {

    private val _grades = MutableStateFlow(listOf<Grade>())
    val grades: StateFlow<List<Grade>> = _grades.asStateFlow();

    private val _name: MutableStateFlow<String> = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _average: MutableStateFlow<String> = MutableStateFlow("0")
    val average: StateFlow<String> = _average.asStateFlow()

    init {
        chargeScreenData()
    }

    private fun chargeScreenData() {
        viewModelScope.launch(Dispatchers.IO) {
            val grades = async { gradeService.findAll(courseId) }
            val courseName = async { courseService.get(courseId).name }
            val average = async { Utils.numberFormat(gradeService.getAverage(courseId)) }

            withContext(Dispatchers.Main) {
                _grades.value = grades.await()
                _name.value = courseName.await()
                _average.value = average.await()
            }
        }
    }

    @Subscribe
    fun onNewGradeAdded(event: AddNewGradeEvent) {
        chargeScreenData()
    }

    fun onNewGrade() {
        navController.navigate("new_grade/${courseId.toString()}")
    }

    fun onBack() {
        navController.navigate("courses")
    }

}