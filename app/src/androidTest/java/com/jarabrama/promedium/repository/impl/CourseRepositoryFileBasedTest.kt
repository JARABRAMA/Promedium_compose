package com.jarabrama.promedium.repository.impl

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CourseRepositoryFileBasedTest {
    private lateinit var courseRepository: CourseRepositoryFileBased;
    private val file = "test.txt"
    private val spacer = "|"

    @Before
    fun onBefore() {
        courseRepository = CourseRepositoryFileBased(fileName = file, separator = spacer)
    }

    @Test
    fun return_an_empty_list() {
        val courses = courseRepository.findAll()
        assert(courses.isEmpty())
    }
}