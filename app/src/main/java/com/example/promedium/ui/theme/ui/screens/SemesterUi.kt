package com.example.promedium.ui.theme.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.promedium.R
import com.example.promedium.ui.theme.model.Course
import com.example.promedium.ui.theme.ui.screens.comon.TopBar
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
        floatingActionButton = { Fab(viewModel = viewModel) },
        floatingActionButtonPosition = FabPosition.End,
        topBar = { TopBar(stringResource(id = R.string.app_name)) },
        bottomBar = { BottomBar(viewModel = viewModel) }
    )

}

@Composable
private fun BottomBar(viewModel: SemesterViewModel) {
    val paddingValues = PaddingValues(16.dp)
    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(fraction = 0.2f)) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.average),
                modifier = Modifier.padding(paddingValues)
            )
            val average = viewModel.getCreditAverage()
            Card (
                modifier= Modifier.padding(paddingValues),
                shape = RoundedCornerShape(16.dp)
            ){
                Text(
                    text = average,
                    modifier = Modifier.padding(paddingValues),
                )
            }
        }
    }
}

@Composable
private fun Fab(viewModel: SemesterViewModel) {
    FloatingActionButton(
        onClick = { viewModel.navigateNewCourseScreen() },
        contentColor = Color.Black,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "new course"
            )
        }
    )
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
    courses.mapIndexed { index, course -> CourseItem(viewModel = viewModel, course = course, positionCourse = index) }
}

@OptIn(ExperimentalMaterial3Api::class)
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
