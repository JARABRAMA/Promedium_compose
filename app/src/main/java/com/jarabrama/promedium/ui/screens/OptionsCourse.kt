package com.jarabrama.promedium.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.jarabrama.promedium.R
import com.jarabrama.promedium.ui.theme.smallPadding
import com.jarabrama.promedium.ui.viewModel.OptionsCourseViewModel

@Composable
fun OptionsCourse(viewModel: OptionsCourseViewModel) {
    Dialog(onDismissRequest = { viewModel.onBack() }) {
        Content(onDelete = { viewModel.onEdit() }, onEdit = { viewModel.onEdit() })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewContent() {
    Content(onDelete = { }) {
    }
}


@Composable
fun Content(onDelete: () -> Unit, onEdit: () -> Unit) {
    val color = MaterialTheme.colorScheme.primaryContainer
    Card(
        colors = CardDefaults.cardColors(
            contentColor = color
        ),
    ) {
        ButtonPopUp(
            onClick = { onDelete() },
            stringResource(R.string.delete),
            R.drawable.delete
        )
        ButtonPopUp(
            onClick = { onEdit() },
            message = stringResource(id = R.string.edit),
            drawable = R.drawable.edit
        )
    }
}

@Composable
private fun ButtonPopUp(onClick: () -> Unit, message: String, drawable: Int) {
    Button(onClick = { onClick() }, modifier = Modifier.padding(smallPadding)) {
        Row {
            Icon(painterResource(id = drawable), contentDescription = null)
            Text(
                text = message, modifier = Modifier.padding(
                    smallPadding
                )
            )
        }
    }
}


