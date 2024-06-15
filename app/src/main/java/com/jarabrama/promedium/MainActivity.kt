package com.jarabrama.promedium

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jarabrama.promedium.repository.impl.CourseRepositoryFileBased
import com.jarabrama.promedium.service.impl.CourseServiceImpl
import com.jarabrama.promedium.ui.viewModel.CourseViewModel
import com.jarabrama.promedium.ui.screens.CourseScreen
import com.jarabrama.promedium.ui.screens.NewCourseScreen
import com.jarabrama.promedium.ui.theme.PromediumTheme
import com.jarabrama.promedium.ui.viewModel.NewCourseViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PromediumTheme {
                val navController = rememberNavController()
                val courseRepository = CourseRepositoryFileBased(this)
                val courseService = CourseServiceImpl(courseRepository)
                val courseViewModel = CourseViewModel(courseService, navController)
                val newCourseViewModel = NewCourseViewModel(courseService = courseService, navController = navController)

                NavHost(navController = navController, startDestination = "courses"){
                    composable(route = "courses"){
                        CourseScreen(viewModel = courseViewModel)
                    }
                    composable(route = "new-course"){
                        NewCourseScreen(newCourseViewModel)
                    }
                }
            }
        }
    }
}

