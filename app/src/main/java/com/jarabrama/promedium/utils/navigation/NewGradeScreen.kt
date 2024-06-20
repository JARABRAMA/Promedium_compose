package com.jarabrama.promedium.utils.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

object NewGradeScreen {
    const val courseId = "courseId"
    const val route = "new_grade/{$courseId}"

    val arguments = listOf(navArgument(courseId) { type = NavType.IntType })
}