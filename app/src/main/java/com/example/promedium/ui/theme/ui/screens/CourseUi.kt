package com.example.promedium.ui.theme.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.promedium.R
import com.example.promedium.ui.theme.model.Course
import com.example.promedium.ui.theme.model.Grade
import com.example.promedium.ui.theme.ui.theme.extraLargePadding
import com.example.promedium.ui.theme.ui.view_model.CourseViewModel

@Composable
fun CourseUi(viewModel: CourseViewModel) {
    val paddingValues = PaddingValues(16.dp)
    val position = viewModel.getPosition()
    val course by viewModel.course.collectAsState()
    Scaffold(
        topBar = { TopBar(paddingValues, viewModel = viewModel) },
        floatingActionButtonPosition = FabPosition.Center,
        content = {
            GradesBox(
                paddingValues = it,
                viewModel = viewModel
            )
        },
        bottomBar = {
            MyBottomBar(
                position = position,
                onNewGrade = { viewModel.onNewGrade(it) },
                course = course
            )
        }
    )
}

@Composable
private fun MyBottomBar(position: Int, onNewGrade: (Int) -> Unit, course: Course) {

    Card(
        shape = RoundedCornerShape(20),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.average),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(extraLargePadding)
                        .fillMaxWidth()
                )
                Text(
                    text = course.average().toString(),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }

            Button(
                onClick = { onNewGrade(position) },
                shape = CircleShape,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(painter = painterResource(R.drawable.add), contentDescription = "new grade")
            }
        }
    }
}

@Composable
private fun GradesBox(paddingValues: PaddingValues, viewModel: CourseViewModel) {
    val padding = PaddingValues(
        top = paddingValues.calculateTopPadding(),
        start = 16.dp,
        end = 16.dp,
        bottom = 16.dp
    )
    Card(
        modifier = Modifier
            .padding(padding)
            .height(0.12f.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )

    ) {
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            GradesItems(viewModel = viewModel)
        }
    }
}

@Composable
fun GradesItems(viewModel: CourseViewModel) {
    val course by viewModel.course.collectAsState()
    val paddingValues = PaddingValues(16.dp)
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        course.grades.forEach { grade: Grade ->
            GradeItem(
                grade = grade,
                paddingValues = paddingValues
            )
        }
    }
}

@Composable
fun GradeItem(grade: Grade, paddingValues: PaddingValues) {
    Card(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = grade.name, // name of the grade
                modifier = Modifier.padding(16.dp),
                fontSize = 18.sp,
                color = Color.White
            )
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = grade.qualification.toString(), // qualification of the grade
                    modifier = Modifier.padding(8.dp),
                    fontSize = 12.sp,
                )
                Text(
                    text = grade.percentage.toString(), // percentage of the grade
                    modifier = Modifier.padding(8.dp),
                    fontSize = 12.sp,
                )
            }
        }
    }
}

@Composable
private fun TopBar(paddingValues: PaddingValues, viewModel: CourseViewModel) {
    val course by viewModel.course.collectAsState()

    Card(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = course.name, // Name of the course
            modifier = Modifier.padding(paddingValues),
            fontSize = 26.sp
        )
    }
}
