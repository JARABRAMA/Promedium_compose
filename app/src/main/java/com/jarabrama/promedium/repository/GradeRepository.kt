package com.jarabrama.promedium.repository

import com.jarabrama.promedium.model.Grade

interface GradeRepository {
    fun findAll(courseId: Int): List<Grade>
    fun save(grade: Grade): Grade
    fun delete(grade: Grade)
    fun update(grades: List<Grade>)
}