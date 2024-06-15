package com.jarabrama.promedium.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.jarabrama.promedium.ui.viewModel.CourseViewModel
import com.jarabrama.promedium.ui.theme.bigPadding
import com.jarabrama.promedium.ui.theme.normal
import com.jarabrama.promedium.ui.theme.normalPadding

@Composable
fun CourseScreen(viewModel: CourseViewModel, ) {
    val courses by viewModel.courses.observeAsState()

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        content = { courses?.let { courseList -> Content(paddingValues = it, courses = courseList) } },
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
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
    ) {
        Column(

        ) {
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


