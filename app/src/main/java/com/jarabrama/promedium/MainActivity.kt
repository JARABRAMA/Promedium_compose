package com.jarabrama.promedium

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jarabrama.promedium.repository.impl.CourseRepositoryFileBased
import com.jarabrama.promedium.repository.impl.GradeRepositoryFileBased
import com.jarabrama.promedium.service.impl.CourseServiceImpl
import com.jarabrama.promedium.service.impl.GradeServiceImpl
import com.jarabrama.promedium.ui.viewModel.CourseViewModel
import com.jarabrama.promedium.ui.screens.CourseScreen
import com.jarabrama.promedium.ui.screens.GradesScreen
import com.jarabrama.promedium.ui.screens.NewCourseScreen
import com.jarabrama.promedium.ui.screens.NewGradeScreen
import com.jarabrama.promedium.ui.theme.PromediumTheme
import com.jarabrama.promedium.ui.viewModel.GradeViewModel
import com.jarabrama.promedium.ui.viewModel.NewCourseViewModel
import com.jarabrama.promedium.ui.viewModel.NewGradeViewModel
import com.jarabrama.promedium.utils.navigation.GradeScreen
import com.jarabrama.promedium.utils.navigation.NewGradeScreen

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
                val newCourseViewModel =
                    NewCourseViewModel(courseService = courseService, navController = navController)
                val gradeRepository = GradeRepositoryFileBased(this)
                val gradeService = GradeServiceImpl(gradeRepository)

                NavHost(navController = navController, startDestination = "courses") {
                    composable(route = "courses") {
                        CourseScreen(viewModel = courseViewModel)
                    }
                    composable(route = "new-course") {
                        NewCourseScreen(newCourseViewModel)
                    }
                    composable(route = GradeScreen.route, arguments = GradeScreen.arguments) {
                        val courseId: Int = it.arguments?.getInt(GradeScreen.courseId) ?: 0
                        val gradeViewModel = GradeViewModel(
                            courseService = courseService,
                            gradeService = gradeService,
                            courseId = courseId,
                            navController = navController
                        )
                        GradesScreen(viewModel = gradeViewModel)
                    }
                    composable(route = NewGradeScreen.route, arguments = NewGradeScreen.arguments) {
                        val courseId: Int = it.arguments?.getInt(NewGradeScreen.courseId) ?: 0
                        val newGradeViewModel = NewGradeViewModel(
                            gradeService, courseId
                        )
                        NewGradeScreen(viewModel = newGradeViewModel)

                    }
                }
            }
        }
    }
}




