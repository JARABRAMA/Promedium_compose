package com.jarabrama.promedium.exceptions

import com.jarabrama.promedium.model.Course

class CourseNotFoundException(id: Int): CourseException("course id '${id}' not found") {
}