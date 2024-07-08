package com.jarabrama.promedium.ui.viewModel

import android.nfc.FormatException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.jarabrama.promedium.exceptions.gradeExceptions.InvalidPercentageException
import com.jarabrama.promedium.service.GradeService
import com.jarabrama.promedium.utils.event.AddNewGradeEvent
import com.jarabrama.promedium.utils.event.InvalidInputEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.greenrobot.eventbus.EventBus
import java.lang.Double.parseDouble

class NewGradeViewModel(
    private val gradeService: GradeService,
    private val courseId: Int,
    private val navController: NavController
) : ViewModel() {

    private val eventBus: EventBus = EventBus.getDefault();

    private val _gradeName = MutableStateFlow("")
    val gradeName: StateFlow<String> = _gradeName

    private val _qualification = MutableStateFlow("")
    val qualification: StateFlow<String> = _qualification

    private val _percentage = MutableStateFlow("")
    val percentage: StateFlow<String> = _percentage

    fun onCreteGrade(name: String, qualification: String, percentage: String) {
        try {
            gradeService.newGrade(
                courseId = courseId,
                name = name,
                qualification = parseDouble(qualification),
                percentage = parseDouble(percentage)
            )
            eventBus.post(
                AddNewGradeEvent(
                    courseId,
                    name,
                    parseDouble(qualification),
                    parseDouble(percentage)
                )
            )
            goBack()
        } catch (e: NumberFormatException) {
            eventBus.post(InvalidInputEvent("percentage and qualification must be decimal numbers"))
            Log.e("NewGradeViewModel", e.message, e)
        } catch (e: InvalidPercentageException) {
            Log.e("NewGradeViewModel", e.message, e)
            eventBus.post(InvalidInputEvent(e.message?: ""))
        }
    }

    private fun goBack() {
        navController.popBackStack()
    }

    fun onNameChange(name: String) {
        _gradeName.value = name;
    }

    fun onPercentageChange(percentageValue: String) {
        try {
            _percentage.value = percentageValue
        } catch (e: FormatException) {
            Log.e("onPercentageChange", e.message, e)
        }
    }

    fun onQualificationChange(qualificationValue: String) {
        try {
            _qualification.value = qualificationValue
        } catch (e: FormatException) {
            Log.e("onPercentageChange", e.message, e)
        }
    }

    fun onBack() {
        navController.popBackStack()
    }
}