package com.jarabrama.promedium.utils.event

data class AddNewGradeEvent(
    val courseId: Int,
    val name: String,
    val qualification: Double,
    val percentage: Double
)