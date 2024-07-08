package com.jarabrama.promedium.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.jarabrama.promedium.R
import com.jarabrama.promedium.model.Course
import com.jarabrama.promedium.ui.theme.big
import com.jarabrama.promedium.ui.theme.columnContainerPadding
import com.jarabrama.promedium.ui.theme.elementListPadding
import com.jarabrama.promedium.ui.theme.normal
import com.jarabrama.promedium.ui.theme.normalElevation
import com.jarabrama.promedium.ui.theme.normalPadding
import com.jarabrama.promedium.ui.theme.smallBorder
import com.jarabrama.promedium.ui.theme.smallElevation
import com.jarabrama.promedium.ui.viewModel.CourseViewModel

@Composable
fun CourseScreen(viewModel: CourseViewModel) {
    val courses by viewModel.courses.collectAsState()
    val average by viewModel.average.collectAsState()
    val showDialog by viewModel.showOptionsDialog.collectAsState()
    val courseSelected by viewModel.courseSelected.collectAsState()

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        content = {
            CoursesColumn(
                paddingValues = it,
                courses = courses,
                onItemClick = { courseId -> viewModel.onCourseClick(courseId) },
                onLongClick = { course -> viewModel.onLongCourseClick(course) },
                showDialog = showDialog,
                onDelete = { courseId -> viewModel.onDelete(courseId) },
                onEdit = { courseId -> viewModel.onEdit(courseId) },
                onDismiss = { viewModel.onCloseDialog() },
                courseSelected = courseSelected
            )
        },
        topBar = { TopBar(title = stringResource(id = R.string.courses)) },
        bottomBar = { AverageBar(average = average) },
        floatingActionButton = { FloatingButton { viewModel.onNewCourse() } }
    )
}

@Composable
fun CoursesColumn(
    courses: List<Course>,
    paddingValues: PaddingValues,
    onItemClick: (Int) -> Unit,
    onLongClick: (Course) -> Unit,
    showDialog: Boolean,
    onEdit: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onDismiss: () -> Unit,
    courseSelected: Course
) {
    Column(
        modifier = Modifier
            .padding(paddingValues),
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
                    .padding(columnContainerPadding),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                courses.forEach { course ->
                    CourseItem(
                        course,
                        onItemClick = { onItemClick(it) },
                        onLongClick = { courseSelected -> onLongClick(courseSelected) },
                        showDialog = showDialog,
                        onEdit = { onEdit(it) },
                        onDelete = { onDelete(it) },
                        onDismiss = { onDismiss() },
                        selectedCourse = courseSelected
                    )
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
    onLongClick: (Course) -> Unit,
    showDialog: Boolean,
    onEdit: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onDismiss: () -> Unit,
    selectedCourse: Course
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(elementListPadding)
            .combinedClickable(
                onClick = { onItemClick(course.id) },
                onLongClick = { onLongClick(course) }
            ),

        shape = RoundedCornerShape(smallBorder)
    ) {
        Column {
            Card(
                colors = CardDefaults.cardColors(
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = course.name,
                        modifier = Modifier.padding(normalPadding),
                        fontSize = big,
                        textDecoration = TextDecoration.Underline
                    )
                    Icon(painter = painterResource(id = R.drawable.next), contentDescription = null)
                }
            }
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CourseText(stringResource(id = R.string.credits))

                CourseText("${course.credits}", fontWeight = FontWeight.Bold)
            }
        }

        if (showDialog) {
            OptionsCourse(
                onEdit = { onEdit(selectedCourse.id) },
                onDelete = { onDelete(selectedCourse.id) },
                onBack = { onDismiss() },
                courseName = selectedCourse.name
            )
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


