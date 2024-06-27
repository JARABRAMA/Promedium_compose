package com.jarabrama.promedium.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jarabrama.promedium.R
import com.jarabrama.promedium.model.Course
import com.jarabrama.promedium.ui.theme.big
import com.jarabrama.promedium.ui.theme.normal
import com.jarabrama.promedium.ui.theme.normalPadding
import com.jarabrama.promedium.ui.theme.small
import com.jarabrama.promedium.ui.theme.smallPadding
import com.jarabrama.promedium.ui.viewModel.CourseViewModel

@Composable
fun CourseScreen(viewModel: CourseViewModel) {
    val courses by viewModel.courses.observeAsState()
    val average by viewModel.average.observeAsState()
    Scaffold(
        content = {
            courses?.let { courseList ->
                CoursesColumn(
                    paddingValues = it,
                    courses = courseList
                ) { courseId ->
                    viewModel.onCourseClick(courseId)
                }
            }
        },
        topBar = { TopBar(title = stringResource(id = R.string.app_name)) },
        bottomBar = { AverageBar(average = average?: "0") },
        floatingActionButton = { FloatingButton { viewModel.onNewCourse() } }
    )
}

@Composable
fun CoursesColumn(courses: List<Course>, paddingValues: PaddingValues, onItemClick: (Int) -> Unit) {
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            shape = CutCornerShape(1.dp)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                courses.forEach { course ->
                    CourseItem(course) { onItemClick(it) }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseItem(course: Course, onItemClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(normalPadding),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        onClick = { onItemClick(course.id) },
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Column {
            Text(text = course.name, modifier = Modifier.padding(normalPadding), fontSize = big)
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CourseText(stringResource(id = R.string.credits))

                CourseText("${course.credits}", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CourseText(
    text: String,
    fontStyle: FontStyle? = FontStyle.Normal,
    fontWeight: FontWeight? = FontWeight.Normal
) {
    Text(
        text = text,
        modifier = Modifier.padding(normalPadding),
        fontSize = normal,
        fontWeight = fontWeight,
        fontStyle = fontStyle
    )
}


