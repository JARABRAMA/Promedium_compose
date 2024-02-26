package com.example.promedium.ui.theme.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.promedium.R
import com.example.promedium.ui.theme.ui.theme.bigPadding
import com.example.promedium.ui.theme.ui.view_model.NewCourseViewModel

@Composable
fun NewCourseUi(viewModel: NewCourseViewModel) {
    val name by viewModel.nameCourse.collectAsState()


    Box(
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.background
        ),
        content = {
            Content(
                viewModel = viewModel,
                name = name
            ) {
                viewModel.onNameChange(it)
            }
        }
    )

}

@Composable
private fun Content(viewModel: NewCourseViewModel, name: String, onNameChange: (String) -> Unit) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(paddingValues = bigPadding)
            .fillMaxSize()

    ) {
        Text(
            text = stringResource(R.string.new_course),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.inversePrimary
        )
        CardFields(
            modifier = Modifier.padding(paddingValues = bigPadding),
            viewModel = viewModel,
            onNameChange = onNameChange,
            name = name
        )
        Button(onClick = {
            val createdCourse = viewModel.addNewCourse()

            if (createdCourse) {
                viewModel.navigateSemesterScreen()
                viewModel.clearValues()
            } else {
                //will appear a small screen announcing a warning
            }
        }) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Composable
fun CardFields(
    modifier: Modifier,
    viewModel: NewCourseViewModel,
    name: String,
    onNameChange: (String) -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(modifier = modifier) {
            Text(
                text = stringResource(id = R.string.new_name_course),
                modifier = Modifier.padding(PaddingValues(start = 25.dp, top = 25.dp))
            )
            NameField(name = name, onNameChange = onNameChange) // this is the field of name item
            Spacer(modifier = Modifier.padding(PaddingValues(20.dp)))
            Text(
                text = stringResource(id = R.string.new_credits_course),
                modifier = Modifier.padding(PaddingValues(start = 25.dp))
            )
            CreditsField(viewModel = viewModel)
        }
    }
}

@Composable
fun CreditsField(viewModel: NewCourseViewModel) {
    val paddingValues = PaddingValues(
        top = 25.dp,
        start = 25.dp,
        end = 25.dp,
        bottom = 25.dp
    )
    val credits by viewModel.creditCourse.collectAsState()
    OutlinedTextField(
        value = credits,
        onValueChange = { viewModel.onCreditsChange(it) },
        shape = shapes.large,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black
        ),
        modifier = Modifier
            .padding(paddingValues)

    )
}

@Composable
fun NameField(name: String, onNameChange: (String) -> Unit) {
    val paddingValues = PaddingValues(
        top = 25.dp,
        start = 25.dp,
        end = 25.dp,
        bottom = 7.dp
    )


    OutlinedTextField(
        value = name,
        onValueChange = { onNameChange(it) },
        shape = shapes.large,
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black
        ),
        modifier = Modifier
            .padding(paddingValues)
    )
}






