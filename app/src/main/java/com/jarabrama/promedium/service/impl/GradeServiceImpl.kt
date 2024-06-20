package com.jarabrama.promedium.service.impl

import com.jarabrama.promedium.exceptions.courseExceptions.CourseNotFoundException
import com.jarabrama.promedium.exceptions.gradeExceptions.GradeNotFoundException
import com.jarabrama.promedium.exceptions.gradeExceptions.SavingGradeException
import com.jarabrama.promedium.model.Grade
import com.jarabrama.promedium.service.GradeService;
import com.jarabrama.promedium.repository.GradeRepository

class GradeServiceImpl(private val gradeRepository: GradeRepository) : GradeService {

    override fun findAll(courseId: Int): List<Grade> = gradeRepository.findAll(courseId)

    override fun newGrade(
        courseId: Int,
        name: String,
        qualification: Double,
        percentage: Double
    ): Grade {
        val grades: MutableList<Grade> = gradeRepository.grades()
        val newGrade = Grade(
            id = grades.size,
            courseId = courseId,
            name = name,
            qualification = qualification,
            percentage = percentage
        )
        grades.add(newGrade)
        val saved: Boolean = gradeRepository.save(grades)
        if (saved) {
            return newGrade
        } else {
            throw SavingGradeException(grade = newGrade)
        }
    }

    override fun update(grade: Grade): Grade {
        val grades = gradeRepository.grades()
        val index: Int = grades.indexOf(grade)
        if (-1 != index) {
            grades[index] = grade
            return grade
        } else {
            throw GradeNotFoundException(grade.id)
        }
    }

    override fun delete(id: Int) {
        val foundedGrade = gradeRepository.get(id) ?: throw GradeNotFoundException(id)
        val grades = gradeRepository.grades()
        grades.remove(foundedGrade)
        gradeRepository.save(grades = grades)
    }

    override fun get(id: Int): Grade = gradeRepository.get(id)?: throw CourseNotFoundException(id)

    override fun getAverage(courseId: Int): Double {
        return 0.0
    }
}