package com.example.promedium.ui.theme.repository

import com.example.promedium.ui.theme.model.Course

interface SemesterRepository {
    fun findAll(): MutableList<Course>
    fun add(course: Course): Course?
    fun get(index: Int): Course
    fun save()
}