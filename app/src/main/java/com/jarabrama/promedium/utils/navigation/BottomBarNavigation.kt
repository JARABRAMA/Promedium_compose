package com.jarabrama.promedium.utils.navigation
import com.jarabrama.promedium.R
sealed class BottomBarNavigation(
    val route: String,
    val title: String,
    val idIcon: Int
) {
    object Courses : BottomBarNavigation(
        route = "courses",
        title = "Courses",
        idIcon = R.drawable.book
    )
    object Grades : BottomBarNavigation(
        route = "grades",
        title = "Grade",
        idIcon = R.drawable.stack
    )
    object Settings : BottomBarNavigation(
        route = "settings",
        title = "Settings",
        idIcon = R.drawable.settings
    )
}