package com.jarabrama.promedium.repository.impl

import com.jarabrama.promedium.repository.CourseRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class CourseRepositoryFileBasedTest {
    private lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun beforeOn() {
        val file = "test.txt"
        val separator = "|"
        courseRepository = CourseRepositoryFileBased(separator = separator, fileName = file)
    }

    @Test
    fun `when there is not any course in the text file`(){
        val courses = courseRepository.findAll()
        assertTrue(courses.isEmpty())
    }

}
