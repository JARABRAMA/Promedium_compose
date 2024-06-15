package com.jarabrama.promedium.exceptions.gradeExceptions

class GradeNotFoundException(id: Int): GradeException("grade $id not found"){
}