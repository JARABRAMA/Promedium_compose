package com.jarabrama.promedium

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jarabrama.promedium.repository.CourseRepository
import com.jarabrama.promedium.repository.GradeRepository
import com.jarabrama.promedium.repository.impl.CourseRepositoryFileBased
import com.jarabrama.promedium.repository.impl.GradeRepositoryFileBased
import com.jarabrama.promedium.service.CourseService
import com.jarabrama.promedium.service.GradeService
import com.jarabrama.promedium.service.impl.CourseServiceImpl
import com.jarabrama.promedium.service.impl.GradeServiceImpl
import com.jarabrama.promedium.ui.screens.CourseScreen
import com.jarabrama.promedium.ui.screens.EditCourseScreen
import com.jarabrama.promedium.ui.screens.GradesScreen
import com.jarabrama.promedium.ui.screens.NewCourseScreen
import com.jarabrama.promedium.ui.screens.NewGradeScreen
import com.jarabrama.promedium.ui.theme.PromediumTheme
import com.jarabrama.promedium.ui.viewModel.CourseViewModel
import com.jarabrama.promedium.ui.viewModel.EditCourseViewModel
import com.jarabrama.promedium.ui.viewModel.GradeViewModel
import com.jarabrama.promedium.ui.viewModel.NewCourseViewModel
import com.jarabrama.promedium.ui.viewModel.NewGradeViewModel
import com.jarabrama.promedium.utils.event.InvalidInputEvent
import com.jarabrama.promedium.utils.navigation.EditCourseScreen
import com.jarabrama.promedium.utils.navigation.GradeScreen
import com.jarabrama.promedium.utils.navigation.NewGradeScreen
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : ComponentActivity() {
    private val eventBus = EventBus.getDefault()
    private lateinit var navController: NavHostController

    private lateinit var courseRepository: CourseRepository
    private lateinit var courseService: CourseService
    private lateinit var gradeRepository: GradeRepository
    private lateinit var gradeService: GradeService

    private lateinit var courseViewModel: CourseViewModel
    private lateinit var newCourseViewModel: NewCourseViewModel
    private lateinit var gradeViewModel: GradeViewModel
    private lateinit var newGradeViewModel: NewGradeViewModel
    private lateinit var editCourseViewModel: EditCourseViewModel

    init {
        eventBus.register(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PromediumTheme {
                navController = rememberNavController()

                courseRepository = CourseRepositoryFileBased(this)
                courseService = CourseServiceImpl(courseRepository)
                gradeRepository = GradeRepositoryFileBased(this)
                gradeService = GradeServiceImpl(gradeRepository)

                courseViewModel = CourseViewModel(courseService, navController)
                newCourseViewModel =
                    NewCourseViewModel(courseService = courseService, navController = navController)

                NavHost(navController = navController, startDestination = "courses") {
                    composable(route = "courses") {
                        CourseScreen(viewModel = courseViewModel)
                    }
                    composable(route = "new-course") {
                        NewCourseScreen(newCourseViewModel)
                    }
                    composable(route = GradeScreen.route, arguments = GradeScreen.arguments) {
                        val courseId: Int = it.arguments?.getInt(GradeScreen.courseId) ?: 0
                        gradeViewModel = GradeViewModel(
                            courseService = courseService,
                            gradeService = gradeService,
                            courseId = courseId,
                            navController = navController
                        )
                        GradesScreen(viewModel = gradeViewModel)
                    }
                    composable(route = NewGradeScreen.route, arguments = NewGradeScreen.arguments) {
                        val courseId: Int = it.arguments?.getInt(NewGradeScreen.courseId) ?: 0
                        newGradeViewModel = NewGradeViewModel(
                            gradeService, courseId, navController
                        )
                        NewGradeScreen(viewModel = newGradeViewModel)
                    }
                    composable(route = EditCourseScreen.route, arguments = EditCourseScreen.arguments) {
                        val courseId: Int = it.arguments?.getInt(NewGradeScreen.courseId) ?: 0
                        editCourseViewModel = EditCourseViewModel(courseService, navController, courseId)
                    }
                }
            }
        }
    }
    @Subscribe
    fun onInvalidInputEvent(event: InvalidInputEvent) {
        Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
    }
}




