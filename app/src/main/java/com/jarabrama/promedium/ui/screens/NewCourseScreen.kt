package com.jarabrama.promedium.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.jarabrama.promedium.R
import com.jarabrama.promedium.ui.theme.extraLarge
import com.jarabrama.promedium.ui.theme.normal
import com.jarabrama.promedium.ui.theme.normalPadding
import com.jarabrama.promedium.ui.theme.small
import com.jarabrama.promedium.ui.theme.smallPadding
import com.jarabrama.promedium.ui.viewModel.NewCourseViewModel


@Composable
fun NewCourseScreen(viewModel: NewCourseViewModel) {
    val courseName by viewModel.courseName.collectAsState()
    val credits by viewModel.courseCredits.collectAsState()

    Scaffold {
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

@Composable
private fun Form(
    courseName: String,
    credits: String,
    onNameChange: (String) -> Unit,
    onCreditsChange: (String) -> Unit,
    onAddCourse: () -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        FormNewCourse(
            nameValue = courseName,
            creditsValue = credits,
            onNameChange = { onNameChange(it) },
            onCreditsChange = { onCreditsChange(it) },
            onSaved = { onAddCourse() }
        )
    }
}

@Composable
fun FormNewCourse(
    nameValue: String,
    creditsValue: String,
    onNameChange: (String) -> Unit,
    onCreditsChange: (String) -> Unit,
    onSaved: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.45f),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
    {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .padding(normalPadding)
        ) {
            Text(text = "Add New Course", fontSize = extraLarge)
            Text(text = "Enter the course details", fontSize = small)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(normalPadding),
        ) {
            Text(text = "Course Name", fontSize = normal, modifier = Modifier.padding(smallPadding))
            TextInput(
                value = nameValue,
                onValueChange = { onNameChange(it) },
                keyboardType = KeyboardType.Text,
                placeholder = stringResource(id = R.string.name_course_placeholder)
            )
            Text(text = "Course Name", fontSize = normal, modifier = Modifier.padding(smallPadding))
            TextInput(
                value = creditsValue,
                onValueChange = { onCreditsChange(it) },
                keyboardType = KeyboardType.Text,
                placeholder = stringResource(id = R.string.credits_placeholder)
            )
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                SaveButton(onSaved = { onSaved() })
            }
        }
    }
}


@Composable
fun SaveButton(onSaved: () -> Unit) {
    Button(
        modifier = Modifier.padding(smallPadding),
        onClick = { onSaved() }, colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Text(text = stringResource(id = R.string.save))
    }
}


