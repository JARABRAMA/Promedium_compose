package com.jarabrama.promedium.utils.event

import com.jarabrama.promedium.model.Course

data class AddNewCourseEvent(val name: String, val credits: Int)