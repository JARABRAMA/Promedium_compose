package com.jarabrama.promedium.utils.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

object GradeScreen {
    const val courseId = "courseId"
    const val route = "grade/{$courseId}"
    val arguments = listOf(navArgument(courseId) { type = NavType.IntType })
}