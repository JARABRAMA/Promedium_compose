package com.example.promedium.ui.theme.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize

data class Course (var name: String, var credits: Int? = 0, var grades: @RawValue MutableList<Grade>):Parcelable{
    fun average(): Double {
        var average = 0.0;
        grades.forEach {grade -> average += grade.percentage * grade.qualification }
        return average
    }

}
