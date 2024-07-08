package com.jarabrama.promedium.utils.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

object EditCourseScreen {
    const val courseId = "courseId"
    const val route = "editCourse/{$courseId}"

    val arguments = listOf(navArgument(courseId) { NavType.IntType })
}