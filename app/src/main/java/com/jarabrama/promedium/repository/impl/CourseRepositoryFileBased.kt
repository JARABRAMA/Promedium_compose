package com.jarabrama.promedium.repository.impl

import android.util.Log

import com.jarabrama.promedium.model.Course
import com.jarabrama.promedium.repository.CourseRepository
import java.io.File
import java.io.FileOutputStream
import java.io.FileInputStream
import java.io.IOException
import java.lang.Integer.parseInt

class CourseRepositoryFileBased(private val fileName: String, private val separator: String) :
    CourseRepository {
    private lateinit var file: File

    init {
        try {
            file = File(fileName)
            if (!file.exists()) {
                file.createNewFile()
            }
        } catch (e: IOException) {
            e.message?.let { Log.e("ERROR", it) }
        }
    }

    override fun findAll(): List<Course> {
        val courses: MutableList<Course> = mutableListOf();
        try {
            val content: List<String>
            FileInputStream(file).use { fileInputStream ->
                fileInputStream.bufferedReader().use { bufferedReader ->
                    content = bufferedReader.readLines()
                }
            }
            content.forEach { courses.add(stringToCourse(it)) }
        } catch (e: IOException) {
            e.message?.let { Log.d("ERROR", it) }
        }
        return courses
    }

    private fun stringToCourse(courseString: String): Course {
        val attributeList = courseString.split(separator)
        return Course(parseInt(attributeList[0]), attributeList[1], parseInt(attributeList[2]))
    }

    override fun get(id: Int): Course? {
        val courses = findAll()
        if (findAll().isEmpty()) {
            return null
        }
        return courses.firstOrNull { it.id == id }
    }

    override fun save(courses: MutableList<Course>): Boolean {
        try {
            FileOutputStream(fileName).use { fileOutputStream ->
                fileOutputStream.bufferedWriter().use { bufferedWriter ->
                    courses.forEach {
                        bufferedWriter.write(courseToString(it))
                        bufferedWriter.newLine();
                    }
                }
            }
            return true

        } catch (e: IOException) {
            e.message?.let { Log.d("ERROR", it) }
            return false
        }
    }

    private fun courseToString(course: Course): String {
        return "${course.id}$separator${course.name}$separator${course.credits}"
    }

    override fun delete(id: Int) {
        val course = findAll().firstOrNull { it.id == id }

        if (course != null) {
            val courses = findAll().toMutableList();
            courses.remove(course)
            try {
                FileOutputStream(fileName).use { fileOutputStream ->
                    fileOutputStream.bufferedWriter().use { writer ->
                        courses.forEach {
                            writer.write(courseToString(it))
                            writer.newLine()
                        }
                    }
                }
            } catch (e: IOException) {
                e.message?.let { Log.d("ERROR", it) }
            }
        }
    }
}