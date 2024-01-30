package com.example.promedium.ui.theme.activitys

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.promedium.ui.theme.ui.theme.PromediumTheme
import com.example.promedium.ui.theme.model.Course
import com.example.promedium.ui.theme.ui.screens.CourseUi
import com.example.promedium.ui.theme.ui.screens.NewCourseUi
import com.example.promedium.ui.theme.ui.screens.SemesterUi
import com.example.promedium.ui.theme.ui.view_model.NewCourseViewModel
import com.example.promedium.ui.theme.ui.view_model.SemesterViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PromediumTheme {
                val navController = rememberNavController()
                val newCourseViewModel = NewCourseViewModel(navController)
                val semesterViewModel = SemesterViewModel(navController)
                NavHost(navController = navController, startDestination = "semester") {
                    composable(route = "semester" ){
                        SemesterUi(viewModel = semesterViewModel)
                    }
                    composable(route = "new_course"){
                        NewCourseUi(viewModel = newCourseViewModel)
                    }
                    composable(route = "course/{course}"){
                        val course = it.arguments?.getParcelable<Course>("course")
                        if (course != null) {
                            CourseUi( course = course )
                        }
                    }
                }
            }
        }
    }
}


