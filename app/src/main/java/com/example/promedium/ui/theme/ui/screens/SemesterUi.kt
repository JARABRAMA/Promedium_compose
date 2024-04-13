package com.example.promedium.ui.theme.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.promedium.R
import com.example.promedium.ui.theme.model.Course
import com.example.promedium.ui.theme.ui.screens.comon.TopBar
import com.example.promedium.ui.theme.ui.theme.bigPadding
import com.example.promedium.ui.theme.ui.theme.mediumPadding
import com.example.promedium.ui.theme.ui.theme.thinPadding
import com.example.promedium.ui.theme.ui.view_model.SemesterViewModel

@Composable
fun SemesterUi(viewModel: SemesterViewModel) {

    Scaffold(
        content = {
            Box(modifier = Modifier
                .padding(it)
                .fillMaxSize(),
                content = { Content(viewModel = viewModel) }
            )
        },
        topBar = { TopBar(stringResource(id = R.string.app_name), TextAlign.Center) },
        bottomBar = {
            SemesterButtonBar(getCreditAverage = { viewModel.getCreditAverage() }) {
                viewModel.navigateNewCourseScreen()
            }
        }
    )

}

@Composable
private fun SemesterButtonBar(getCreditAverage: () -> String, onNewCourse: () -> Unit) {

    Card(
        shape = RoundedCornerShape(20),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.2f)
            .padding(bigPadding)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(mediumPadding)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.average),
                    modifier = Modifier.padding(thinPadding)
                )
                val average = getCreditAverage()
                Text(
                    text = average,
                    modifier = Modifier.padding(thinPadding),
                )
            }
            Button(
                onClick = { onNewCourse() },
                shape = CircleShape,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(painter = painterResource(R.drawable.add), contentDescription = "new grade")
            }
        }
    }
}


@Composable
private fun Content(viewModel: SemesterViewModel) {
    val state = rememberScrollState()

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(state = state),
        ) {
            OnItemCreate(viewModel = viewModel)
        }

    }
}

@Composable
private fun OnItemCreate(viewModel: SemesterViewModel) {
    val courses = viewModel.courses
    courses.mapIndexed { index, course ->
        CourseItem(
            viewModel = viewModel,
            course = course,
            positionCourse = index
        )
    }
}

@Composable
private fun CourseItem(viewModel: SemesterViewModel, course: Course, positionCourse: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = { viewModel.onClickCourse(position = positionCourse) }
    ) {
        Row {
            Text(
                text = course.name,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.padding(25.dp))
            Column {
                Text(text = "credits: ${course.credits}", modifier = Modifier.padding(5.dp))
                Text(text = "", modifier = Modifier.padding(5.dp)) // average

            }
        }
    }
}
