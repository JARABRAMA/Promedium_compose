package com.jarabrama.promedium.service.impl

import com.jarabrama.promedium.exceptions.CourseException
import com.jarabrama.promedium.exceptions.CourseNotFoundException
import com.jarabrama.promedium.exceptions.CoursesNotFoundException
import com.jarabrama.promedium.model.Course
import com.jarabrama.promedium.repository.CourseRepository
import com.jarabrama.promedium.service.CourseService

class CourseServiceImpl(private val courseRepository: CourseRepository) : CourseService {
    override fun findAll(): List<Course> {
        val courses = courseRepository.findAll();
        return courses
    }

    override fun newCourse(name: String, credits: Int): Course {
        val courses = courseRepository.findAll().toMutableList()
        val course = Course(courses.size, name, credits)
        courses.add(course)
        val save = courseRepository.save(courses)
        if (!save) {
            throw CourseException("Can not save the curse")
        }
        return course
    }

    override fun update(course: Course): Course {
        val courses = findAll().toMutableList()
        val index: Int = courses.indexOf(course)
        if (index == -1) {
            throw CourseNotFoundException(course.id)
        }
        courses[index] = course;
        courseRepository.save(courses)
        return course
    }


    override fun delete(id: Int) {
        val course =
            courseRepository.get(id)
                ?: throw CourseNotFoundException(id) // check if the note exists
        courseRepository.delete(id)
    }

    override fun get(id: Int): Course {
        return courseRepository.get(id) ?: throw CourseNotFoundException(id)
    }

    override fun getAverage(): Double {
        var average: Double = 0.0;
        //todo(no yet implemented )
        return average
    }
}