package com.jarabrama.promedium.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jarabrama.promedium.R
import com.jarabrama.promedium.model.Course
import com.jarabrama.promedium.ui.viewModel.CourseViewModel
import com.jarabrama.promedium.ui.theme.bigPadding
import com.jarabrama.promedium.ui.theme.normal
import com.jarabrama.promedium.ui.theme.normalPadding

@Composable
fun CourseScreen(viewModel: CourseViewModel) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        content = { Content(paddingValues = it, courses = viewModel.courses) },
        topBar = { TopBar(title = stringResource(id = R.string.app_name)) },
        bottomBar = { CourseBottomBar { viewModel.onNewCourse() } }
    )
}

@Composable
fun CourseBottomBar(onClickListener: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .padding(bigPadding),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Row(
            Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AverageCard(average = 4.0)
            FloatingButton(onClick = { onClickListener() })
        }
    }
}

@Composable
fun Content(paddingValues: PaddingValues, courses: List<Course>) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        Row {
            CoursesColumn(courses)
        }
    }
}

@Composable
fun CoursesColumn(courses: List<Course>) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = Modifier
            .padding(paddingValues = bigPadding)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            courses.forEach { course ->
                CourseItem(course)
            }
        }
    }
}

@Preview
@Composable
fun PreviewCourse() {
    val course = Course(0, "Differential Equations", 3)
    CourseItem(course)
}

@Composable
fun CourseItem(course: Course) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(normalPadding),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                Modifier.fillMaxWidth(0.5f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CourseText(text = stringResource(id = R.string.course))
                CourseText(
                    text = course.name,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            }
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


