package com.jarabrama.promedium.repository.impl

import android.content.Context
import android.util.Log
import com.jarabrama.promedium.model.Grade
import com.jarabrama.promedium.repository.GradeRepository
import java.io.File
import java.io.IOException
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt
import java.nio.charset.Charset
import java.util.concurrent.Flow

class GradeRepositoryFileBased(private val context: Context) : GradeRepository {

    private val fileAddress: String = "grades.txt"
    private val separator: String = "|"

    init {
        try {
            val file = File(context.filesDir, fileAddress)
            if (!file.exists()) {
                val created = file.createNewFile()
                if (created) {
                    Log.i("GradeRepositoryFileBased: init", "file created: $fileAddress")
                }
            }
        } catch (e: IOException) {
            Log.e("GradeRepositoryFileBased", e.message, e)
        }
    }

    private fun getGrades(): MutableList<Grade> {
        val grades: MutableList<Grade> = mutableListOf()
        try {
            context.openFileInput(fileAddress).bufferedReader().use { reader ->
                reader.forEachLine { line ->
                    grades.add(toGrade(line))
                }
            }
        } catch (e: IOException) {
            Log.e("GradeRepositoryFileBased: getGrades", e.message, e)
        }
        return grades
    }


    override fun findAll(courseId: Int): List<Grade> = getGrades().filter { it.courseId == courseId }


    private fun toGrade(line: String): Grade {
        val parts: List<String> = line.split(separator)
        return Grade(
            parseInt(parts[0]), // id
            parseInt(parts[1]),  // courseId
            parts[2], // name
            parseDouble(parts[3]), // qualification
            parseDouble(parts[4]) //percentage
        )
    }


    override fun save(grades: MutableList<Grade>): Boolean {
        return try {
            context.openFileOutput(fileAddress, Context.MODE_PRIVATE)
                .bufferedWriter(Charset.defaultCharset()).use { writer ->
                    grades.forEach { grade ->
                        writer.write(toDb(grade))
                        writer.newLine()
                    }
                }
            true
        } catch (e: IOException) {
            Log.e("GradeRepositoryFileBased: Save", e.message, e)
            false
        }

    }

    override fun delete(grade: Grade) {
        val grades = getGrades()
        if (grades.remove(grade)) {
            save(grades)
            Log.i("GradeRepositoryFileBased: delete", "grade ${grade.id} removed")
        } else {
            Log.e("GradeRepositoryFileBased: delete", "grade ${grade.id} not founded")
        }
    }

    override fun get(id: Int): Grade? = getGrades().firstOrNull { course -> course.id == id }


    private fun toDb(grade: Grade): String {
        return "${grade.id}$separator${grade.courseId}$separator${grade.name}$separator${grade.qualification}$separator${grade.percentage}"
    }
}