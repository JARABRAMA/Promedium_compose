package com.jarabrama.promedium.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jarabrama.promedium.R
import com.jarabrama.promedium.ui.theme.smallPadding
import com.jarabrama.promedium.ui.viewModel.NewCourseViewModel


@Composable
fun NewCourseScreen(viewModel: NewCourseViewModel) {
    val courseName by viewModel.courseName.collectAsState()
    val credits by viewModel.courseCredits.collectAsState()

    Scaffold(
        topBar = {
            TopAppBarWhitBack(
                title = "New Course",
                onBack = { viewModel.onBack() },
                backTile = null
            ){}
        },
    ) {

        Form(
            courseName = courseName,
            credits = credits,
            onCreditsChange = { credits -> viewModel.onCreditsChange(credits) },
            onAddCourse = { viewModel.onAddCourse() },
            onNameChange = { name -> viewModel.onNameChange(name) },
            paddingValues = it
        )
    }
}

@Preview
@Composable
fun PreviewFormCourse() {
    Form(
        courseName = "",
        credits = "",
        onNameChange = {},
        onCreditsChange = {},
        onAddCourse = {},
        paddingValues = smallPadding
    )
}

@Composable
private fun Form(
    courseName: String,
    credits: String,
    onNameChange: (String) -> Unit,
    onCreditsChange: (String) -> Unit,
    onAddCourse: () -> Unit,
    paddingValues: PaddingValues
) {
    FormCourse(
        title = stringResource(R.string.add_new_course),
        nameValue = courseName,
        creditsValue = credits,
        onNameChange = { onNameChange(it) },
        onCreditsChange = { onCreditsChange(it) },
        onSaved = { onAddCourse() },
        paddingValues = paddingValues
    )
}
