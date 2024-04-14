package com.example.promedium.ui.theme.repository.impl

import com.example.promedium.ui.theme.model.Course
import com.example.promedium.ui.theme.model.Grade
import com.example.promedium.ui.theme.repository.SemesterRepository
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

class FileBasedRepositoryImpl : SemesterRepository {
    private val fileAddress: String = "app/src/main/res/files "
    override fun findAll(): MutableList<Course> {
        val courses: MutableList<Course> = mutableListOf()
        val file = File(fileAddress)
        if (file.exists()) {
            val lines = file.readLines()
            lines.forEach { courses.add(buildCourse(it)) }
        }
        return courses
    }

    override fun add(course: Course): Course? {
        val line: String = course.toString()
        return try {
            BufferedWriter(FileWriter(fileAddress, true)).use {
                it.newLine()
                it.write(line)
            }
            course
        } catch (e: IOException) {
            null
        }
    }

    private fun buildCourse(line: String): Course {
        val course: List<String> = line.split(",")
        val grades: MutableList<Grade> = gradesFileToList(course[3])
        return Course(name = course[0], credits = course[2].toInt(), grades = grades)
    }

    private fun buildGrade(it: String): Grade {
        val grade = it.split(",")
        return Grade(
            name = grade[0],
            percentage = grade[1].toDouble(),
            qualification = grade[2].toDouble()
        )
    }

    private fun gradesFileToList(fileAddress: String): MutableList<Grade> {
        val file = File(fileAddress)
        val result: MutableList<Grade> = mutableListOf()
        if (file.exists()) {
            val lines = file.readLines()
            lines.forEach { result.add(buildGrade(it)) }
        }
        return result
    }


    override fun get(index: Int): Course {
        return findAll()[index]
    }

    override fun save() {
        TODO("Not yet implemented")
    }
}