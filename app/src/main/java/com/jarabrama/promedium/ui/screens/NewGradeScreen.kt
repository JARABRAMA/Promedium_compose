package com.jarabrama.promedium.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jarabrama.promedium.R
import com.jarabrama.promedium.ui.theme.big
import com.jarabrama.promedium.ui.theme.extraLarge
import com.jarabrama.promedium.ui.theme.normal
import com.jarabrama.promedium.ui.theme.normalPadding
import com.jarabrama.promedium.ui.theme.small
import com.jarabrama.promedium.ui.theme.smallPadding
import com.jarabrama.promedium.ui.viewModel.NewGradeViewModel

@Composable
fun NewGradeScreen(viewModel: NewGradeViewModel) {
    val name by viewModel.gradeName.collectAsState()
    val percentage by viewModel.percentage.collectAsState()
    val qualification by viewModel.qualification.collectAsState()

    Scaffold(
        topBar = {
            TopAppBarWhitBack(title = stringResource(R.string.new_grade_title), backTile = null, onBack = {viewModel.onBack()}) {}
        }
    ) {
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
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            Modifier
                .padding(PaddingValues(top = 10.dp))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.padding(normalPadding))
            Text(
                text = stringResource(R.string.grade_details),
                fontSize = big,
                modifier = Modifier.fillMaxWidth(0.8f),
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.padding(normalPadding))

            Text(
                text = stringResource(id = R.string.grade_name),
                Modifier
                    .fillMaxWidth(.8f)
                    .padding(smallPadding),
                textAlign = TextAlign.Left,
                fontSize = normal
            )
            TextField(
                value = nameValue,
                onValueChange = { onNameChange(it) },
                modifier = Modifier.fillMaxWidth(.8f),
                singleLine = true,
                maxLines = 1,
                placeholder = { NamePlaceholder() }
            )

            Text(
                text = stringResource(id = R.string.qualification),
                Modifier
                    .fillMaxWidth(.8f)
                    .padding(smallPadding),
                textAlign = TextAlign.Left,
                fontSize = normal
            )
            TextField(
                value = qualificationValue,
                onValueChange = { onQualificationChange(it) },
                Modifier.fillMaxWidth(0.8f),
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                placeholder = { QualificationPlaceHolder() }
            )

            Text(
                text = stringResource(id = R.string.percentage),
                Modifier
                    .fillMaxWidth(.8f)
                    .padding(smallPadding),
                textAlign = TextAlign.Left,
                fontSize = normal
            )
            TextField(
                value = percentageValue,
                onValueChange = { onPercentageChange(it) },
                Modifier.fillMaxWidth(0.8f),
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                placeholder = { PercentagePlaceholder() }
            )
            Spacer(modifier = Modifier.padding(normalPadding))
            SaveButton {
                onSave(nameValue, qualificationValue, percentageValue)
            }

        }
    }
}

@Composable
fun PercentagePlaceholder() {
    Row(Modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.percentage))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Icon(painterResource(id = R.drawable.percentage), null)
        }
    }
}

@Composable
fun QualificationPlaceHolder() {
    Row(Modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.qualification))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Icon(painterResource(id = R.drawable.equalizer), null)
        }
    }
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


