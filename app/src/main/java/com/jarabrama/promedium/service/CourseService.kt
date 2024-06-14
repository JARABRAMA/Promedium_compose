package com.jarabrama.promedium.service

import com.jarabrama.promedium.model.Course

interface CourseService {
    fun findAll(): List<Course>
    fun newCourse(name: String, credits: Int): Course
    fun update(course: Course): Course
    fun delete(id: Int)
    fun get(id: Int): Course
    fun getAverage(): Double
}