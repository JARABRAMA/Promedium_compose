package com.jarabrama.promedium.repository

import com.jarabrama.promedium.model.Grade

interface GradeRepository {
    fun grades(): MutableList<Grade>
    fun findAll(courseId: Int): List<Grade>
    fun save(grades: MutableList<Grade>): Boolean
    fun delete(grade: Grade)
    fun get(id: Int): Grade?
}