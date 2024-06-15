package com.jarabrama.promedium.exceptions.gradeExceptions

import com.jarabrama.promedium.model.Grade

class SavingGradeException(grade: Grade): GradeException("Can't save $grade") {
}