package com.jarabrama.promedium.utils.navigation

import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object OptionsCourseScreen {
    const val courseId = "courseId"
    val route = "optionsCourse/{$courseId}"
    val arguments = listOf(navArgument(courseId) {type = NavType.IntType})
}