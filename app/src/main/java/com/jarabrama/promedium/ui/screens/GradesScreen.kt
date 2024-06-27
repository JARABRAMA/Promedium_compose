package com.jarabrama.promedium.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jarabrama.promedium.R
import com.jarabrama.promedium.model.Grade
import com.jarabrama.promedium.ui.theme.big
import com.jarabrama.promedium.ui.theme.bigPadding
import com.jarabrama.promedium.ui.theme.normal
import com.jarabrama.promedium.ui.theme.normalPadding
import com.jarabrama.promedium.ui.theme.smallPadding
import com.jarabrama.promedium.ui.viewModel.GradeViewModel

@Composable
fun GradesScreen(viewModel: GradeViewModel) {
    val name by viewModel.name.collectAsState()
    val grades by viewModel.grades.collectAsState()
    val average by viewModel.average.collectAsState()
    Scaffold(
        content = { GradeList(it, grades) },
        topBar = {
            TopAppBarGrade(title = name) {
                viewModel.onBack()
            }
        },
        floatingActionButton = {
            FloatingButton {
                viewModel.onNewGrade()
            }
        },
        bottomBar = { AverageBar(average) }
    )
}

@Preview
@Composable
fun TopAppBarGradePreview() {
     TopAppBarGrade(title = "Programming techniques") {}
}

@Composable
fun TopAppBarGrade(title: String, onBack: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .systemBarsPadding(),
        shape = RoundedCornerShape(25),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(
                onClick = { onBack() },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
            Text(
                text = title,
                fontSize = big,
                modifier = Modifier.padding(smallPadding),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GradeListPreview() {
    val courses = listOf(
        Grade(1, 2, "parcial 1", 3.5, 20.0),
        Grade(1, 2, "parcial 2", 3.5, 20.0),
        Grade(1, 2, "parcial 3", 6.5, 50.0),
        Grade(1, 2, "parcial 4", 3.5, 40.0),
        Grade(1, 2, "parcial 3", 6.5, 50.0),
        Grade(1, 2, "parcial 3", 6.5, 50.0),
    )

    GradeList(paddingValues = normalPadding, courses)
}

@Composable
fun GradeList(paddingValues: PaddingValues, grades: List<Grade>) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        grades.forEach {
            GradeItem(it)
        }
    }
}

@Preview
@Composable
fun GradeItemPreview() {
    val grade = Grade(1, 3, "final exam", 4.2, 25.0)
    GradeItem(grade)
}

@Composable
fun GradeItem(grade: Grade) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(smallPadding),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Column(modifier = Modifier.padding(smallPadding)) {
            TextGrade(grade.name, fontSize = big)
            Row(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(0.5f)) {
                    TextGrade(text = stringResource(R.string.qualification))
                    TextGrade(text = "${grade.qualification}", fontWeight = FontWeight.Bold)
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextGrade(text = stringResource(R.string.percentage))
                    TextGrade(text = "${grade.percentage}", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun TextGrade(
    text: String,
    fontSize: TextUnit? = normal,
    fontWeight: FontWeight? = FontWeight.Normal
) {

    Text(
        text = text,
        fontSize = fontSize ?: normal,
        fontWeight = fontWeight,
        modifier = Modifier.padding(
            smallPadding
        )
    )
}


