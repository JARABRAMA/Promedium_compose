package com.jarabrama.promedium.exceptions.courseExceptions

class CourseNotFoundException(id: Int): CourseException("course id '${id}' not found") {
}