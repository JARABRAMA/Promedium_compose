package com.jarabrama.promedium.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jarabrama.promedium.R
import com.jarabrama.promedium.ui.theme.bigPadding
import com.jarabrama.promedium.ui.theme.extraLarge
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
            percentageValue = percentage,
            qualificationValue = qualification,
            onNameChange = { nameValue -> viewModel.onNameChange(nameValue) },
            onPercentageChange = { percentageValue -> viewModel.onPercentageChange(percentageValue) },
            onQualificationChange = { qualificationValue ->
                viewModel.onQualificationChange(
                    qualificationValue
                )
            },
            onSave = { name, qualification, percentage ->
                viewModel.onCreteGrade(
                    name,
                    qualification,
                    percentage
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
    onPercentageChange: (String) -> Unit,
    onSave: (String, String, String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Card(
            modifier = Modifier.padding(bigPadding),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(Modifier.padding(smallPadding)) {

                TitleColumn()
                FormColumn(
                    nameValue,
                    percentageValue,
                    qualificationValue,
                    onNameChange,
                    onQualificationChange,
                    onPercentageChange,
                    onSave
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
    onPercentageChange: (String) -> Unit,
    onSave: (String, String, String) -> Unit
) {
    Column(Modifier.padding(PaddingValues(top = 10.dp))) {
        TextForm(stringResource(R.string.grade_name))
        TextInput(
            value = nameValue,
            onValueChange = onNameChange,
            keyboardType = KeyboardType.Text,
            placeholder = stringResource(
                id = R.string.grade_name_placeholder
            )
        )
        TextForm(stringResource(R.string.qualification))
        TextInput(
            value = qualificationValue,
            onValueChange = { newQualificationValue -> onQualificationChange(newQualificationValue) },
            keyboardType = KeyboardType.Decimal,
            placeholder = stringResource(R.string.qualification_placeholder)
        )
        TextForm(stringResource(R.string.percentage))
        TextInput(
            value = percentageValue,
            onValueChange = { newPercentageValue -> onPercentageChange(newPercentageValue) },
            keyboardType = KeyboardType.Decimal,
            placeholder = stringResource(R.string.percentage_placeholder)
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            SaveButton {
                onSave(nameValue, qualificationValue, percentageValue)
            }
        }
    }
}

@Composable
fun TextForm(message: String) {
    Text(text = message, fontSize = normal, modifier = Modifier.padding(smallPadding))
}


@Composable
@Preview
fun TitleColumn() {
    Column(Modifier.padding(smallPadding)) {
        Text(
            text = stringResource(R.string.new_grade),
            fontSize = extraLarge,
        )
        Text(
            text = "Enter the grade's details",
            fontSize = small,
        )
    }
}


