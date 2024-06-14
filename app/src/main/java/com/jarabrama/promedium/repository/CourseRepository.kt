package com.jarabrama.promedium.repository

import com.jarabrama.promedium.model.Course

interface CourseRepository {
    fun findAll(): List<Course>
    fun get(id: Int): Course?
    fun save(courses: MutableList<Course>): Boolean
    fun delete(id: Int)
}

