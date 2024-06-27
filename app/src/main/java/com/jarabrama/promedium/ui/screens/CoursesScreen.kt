package com.jarabrama.promedium.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jarabrama.promedium.R
import com.jarabrama.promedium.model.Course
import com.jarabrama.promedium.ui.theme.big
import com.jarabrama.promedium.ui.theme.normal
import com.jarabrama.promedium.ui.theme.normalPadding
import com.jarabrama.promedium.ui.viewModel.CourseViewModel

@Composable
fun CourseScreen(viewModel: CourseViewModel) {
    val courses by viewModel.courses.observeAsState()
    val average by viewModel.average.observeAsState()
    val showDialog by viewModel.showOptionsDialog.collectAsState()

    Scaffold(
        content = {
            courses?.let { courseList ->
                CoursesColumn(
                    paddingValues = it,
                    courses = courseList,
                    onItemClick = { viewModel.onCourseClick(it) },
                    onLongClick = { viewModel.onLongCourseClick() },
                    showDialog = showDialog,
                    onDelete = { viewModel.onDelete(it) },
                    onEdit = { viewModel.onEdit(it) },
                    onDismiss = { viewModel.onCloseDialog() }
                )
            }
        },
        topBar = { TopBar(title = stringResource(id = R.string.app_name)) },
        bottomBar = { AverageBar(average = average ?: "0") },
        floatingActionButton = { FloatingButton { viewModel.onNewCourse() } }
    )
}

@Composable
fun CoursesColumn(
    courses: List<Course>,
    paddingValues: PaddingValues,
    onItemClick: (Int) -> Unit,
    onLongClick: () -> Unit,
    showDialog: Boolean,
    onEdit: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onDismiss: () -> Unit
) {
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
                    CourseItem(course,
                        onItemClick = { onItemClick(it) },
                        onLongClick = { onLongClick() },
                        showDialog = showDialog,
                        onEdit = { onEdit(it) },
                        onDelete = { onDelete(it) },
                        onDismiss = { onDismiss() })
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CourseItem(
    course: Course,
    onItemClick: (Int) -> Unit,
    onLongClick: () -> Unit,
    showDialog: Boolean,
    onEdit: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(normalPadding)
            .combinedClickable(
                onClick = { onItemClick(course.id) },
                onLongClick = { onLongClick() }
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
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

        if (showDialog) {
            OptionsCourse(onEdit = { onEdit(course.id) }, onDelete = { onDelete(course.id) }, onBack = {onDismiss()}, courseName = course.name)
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


