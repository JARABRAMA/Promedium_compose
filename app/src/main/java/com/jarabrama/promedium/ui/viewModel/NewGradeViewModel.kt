package com.jarabrama.promedium.ui.viewModel

import android.nfc.FormatException
import android.util.Log
import androidx.lifecycle.ViewModel
import com.jarabrama.promedium.service.GradeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Double.parseDouble

class NewGradeViewModel(private val gradeService: GradeService, val courseId: Int): ViewModel() {

    private val _gradeName = MutableStateFlow("")
    val gradeName: StateFlow<String> = _gradeName

    private val _qualification = MutableStateFlow(0.0)
    val qualification: StateFlow<Double> = _qualification

    private val _percentage = MutableStateFlow(0.0)
    val percentage: StateFlow<Double> =  _percentage

    fun onCreteGrade(name: String, qualification: Double, percentage: Double){
        gradeService.newGrade(
            courseId = courseId  ,
            name = name ,
            qualification = qualification,
            percentage = percentage ,
        )
    }

    fun onNameChange(name: String) {
        _gradeName .value = name;
    }

    fun onPercentageChange(percentageValue: String) {
        try {
            _percentage.value = parseDouble(percentageValue)
        } catch (e: FormatException) {
            Log.e("onPercentageChange", e.message, e)
        }
    }

    fun onQualificationChange(qualificationValue: String) {
        try {
            _qualification.value = parseDouble(qualificationValue)
        } catch (e: FormatException) {
            Log.e("onPercentageChange", e.message, e)
        }
    }
}