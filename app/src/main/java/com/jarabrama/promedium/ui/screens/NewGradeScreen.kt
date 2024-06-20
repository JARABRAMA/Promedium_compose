package com.jarabrama.promedium.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jarabrama.promedium.R
import com.jarabrama.promedium.ui.theme.big
import com.jarabrama.promedium.ui.theme.bigPadding
import com.jarabrama.promedium.ui.theme.normal
import com.jarabrama.promedium.ui.theme.small
import com.jarabrama.promedium.ui.theme.smallPadding
import com.jarabrama.promedium.ui.viewModel.NewGradeViewModel

@Composable
fun NewGradeScreen(viewModel: NewGradeViewModel) {
    val name by viewModel.gradeName.collectAsState()
    val percentage by viewModel.percentage.collectAsState()
    val qualification by viewModel.qualification.collectAsState()

    Scaffold {
        FormNewGrade(
            paddingValues = it,
            nameValue = name,
            percentageValue = percentage.toString(),
            qualificationValue = qualification.toString(),
            onNameChange = { nameValue -> viewModel.onNameChange(nameValue) },
            onPercentageChange = { percentageValue -> viewModel.onPercentageChange(percentageValue) },
            onQualificationChange = { qualificationValue ->
                viewModel.onQualificationChange(
                    qualificationValue
                )
            }
        )
    }
}

@Composable
fun FormNewGrade(
    paddingValues: PaddingValues,
    nameValue: String,
    percentageValue: String,
    qualificationValue: String,
    onNameChange: (String) -> Unit,
    onQualificationChange: (String) -> Unit,
    onPercentageChange: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Card(modifier = Modifier.padding(bigPadding)) {
            Column(Modifier.padding(smallPadding)) {

                TitleColumn()
                FormColumn(
                    nameValue,
                    percentageValue,
                    qualificationValue,
                    onNameChange,
                    onQualificationChange,
                    onPercentageChange
                )
            }
        }
    }
}

@Composable
fun FormColumn(
    nameValue: String,
    percentageValue: String,
    qualificationValue: String,
    onNameChange: (String) -> Unit,
    onQualificationChange: (String) -> Unit,
    onPercentageChange: (String) -> Unit
) {
    Column(Modifier.padding(PaddingValues(top = 10.dp))) {
        Text(
            text = stringResource(R.string.grade_name),
            fontSize = normal,
            modifier = Modifier.padding(smallPadding)
        )
        TextInput(
            value = nameValue,
            onValueChange = onNameChange,
            keyboardType = KeyboardType.Text,
            placeholder = stringResource(
                id = R.string.grade_name_placeholder
            )
        )
        NumberInput(
            value = qualificationValue,
            onValueChange = { onQualificationChange(it) }
        )
    }
}

@Composable
fun NumberInput(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        ),
        value = value,
        onValueChange = { newValue -> onValueChange(newValue) },

    )
}


@Composable
@Preview
fun TitleColumn() {
    Column {
        Text(
            text = stringResource(R.string.new_grade),
            fontSize = big,
            modifier = Modifier.padding(
                smallPadding
            )
        )
        Text(
            text = "Enter the grade's details",
            fontSize = small,
            modifier = Modifier.padding(
                smallPadding
            )
        )
    }
}


