package com.jarabrama.promedium.model

data class Grade(
    val id: Int,
    val courseId: Int,
    val name: String,
    val qualification: Double,
    val percentage: Double
)