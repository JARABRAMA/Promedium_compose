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

    override fun findAll(courseId: Int): List<Grade> {
        val allGrades = mutableListOf<Grade>()
        context.openFileInput(fileAddress).use {
            it.bufferedReader(Charset.defaultCharset()).forEachLine { line ->
                allGrades.add(toGrade(line))
            }
        }
        return allGrades.filter { it.courseId == courseId }
    }

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


    override fun save(grade: Grade): Grade {
        TODO("Not yet implemented")
    }

    override fun delete(grade: Grade) {
        TODO("Not yet implemented")
    }

    override fun update(grades: List<Grade>) {
        context.openFileOutput(fileAddress, Context.MODE_PRIVATE).bufferedWriter().use { writer ->
            grades.forEach { grade ->
                writer.write(toDb(grade))
                writer.newLine()
            }
        }
    }

    private fun toDb(grade: Grade): String {
        return "${grade.id}$separator${grade.courseId}$separator${grade.name}$separator${grade.qualification}$separator${grade.percentage}"
    }
}