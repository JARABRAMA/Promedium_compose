package com.jarabrama.promedium.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jarabrama.promedium.R
import com.jarabrama.promedium.ui.viewModel.EditCourseViewModel;

@Composable
fun EditCourseScreen(viewModel: EditCourseViewModel) {
    val name by viewModel.name.collectAsState()
    val credits by viewModel.credits.collectAsState()
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            FormCourse(
                title = stringResource(R.string.edit_your_course),
                nameValue = name,
                creditsValue = credits,
                onNameChange = { nameValue -> viewModel.onNameChange(nameValue) },
                onCreditsChange = { creditsValue -> viewModel.onCreditsChange(creditsValue) },
                onSaved = { viewModel.onSave() },
                paddingValues = it
            )
        }
    }
}

